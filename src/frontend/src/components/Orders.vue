<template>
  <service></service>
  <div class="orders">
    <nav role="navigation" v-if="role === 'agent'">
      <RouterLink class="create" id="create_order" to="/createOrder">Create Order</RouterLink>
    </nav>
  </div>
  <table role="table" aria-label="Orders table" >
    <thead class='thead'>
    <tr>
      <th role="button" scope="col" @click="sort('ordNum')">Order Number</th>
      <th role="button" class="otherInfo"  scope="col" @click="sort('ordAmount')">Order Amount</th>
      <th role="button" class="otherInfo" scope="col" @click="sort('advanceAmount')">Advance Amount</th>
      <th role="button" class="otherInfo" scope="col" @click="sort('ordDate')">Order Date</th>
      <th role="button" class="otherInfo" scope="col" @click="sort('ordDescription')">Order Description</th>
      <th role="button" scope="col" @click="sort('agent')" v-if="role === 'admin' || role === 'customer'">Agent Code</th>
      <th role="button" scope="col" @click="sort('customer')" v-if="role === 'admin' || role === 'agent'">Customer Code</th>
      <th class="infoCol" scope="col" v-if="role === 'admin' || role === 'agent'">Edit</th>
      <th class="infoCol" scope="col" v-if="role === 'agent'">Delete</th>
    </tr>
    </thead>
    <tbody v-for="order in sortedOrders">
    <tr aria-label="row" class="table">
      <td>{{ order.ordNum }}</td>
      <td class="otherInfo">{{ order.ordAmount }}</td>
      <td class="otherInfo">{{ order.advanceAmount }}</td>
      <td class="otherInfo">{{ order.ordDate }}</td>
      <td class="otherInfo">{{ order.ordDescription }}</td>
      <td v-if="role === 'admin' || role === 'customer'">
        <button @click="agentToggle(order.agent.agentCode, order.ordNum)"
                :class="{ agentOpened: agentOpened.includes(order.ordNum) }">{{ order.agent.agentCode }}
        </button>
      </td>
      <td v-if="role === 'admin' || role === 'agent'">
        <button @click="customerToggle(order.customer.custCode, order.ordNum)"
                :class="{ customerOpened: customerOpened.includes(order.ordNum) }">{{ order.customer.custCode }}
        </button>
      </td>
      <td v-if="role === 'admin'|| role === 'agent'">
        <button @click="editOrder(order.ordNum)">Edit</button>
      </td>
      <td v-if="role === 'agent'">
        <button @click="deleteOrder(order.ordNum)">Delete</button>
      </td>
    </tr>
    <tr aria-label="agent info table" class="theadInfo" id="agentHead" v-if="agentOpened.includes(order.ordNum) && role !== 'agent'">
      <th>Agent Name</th>
      <th>Working Area</th>
      <th>Phone Number</th>
      <th>Country</th>
    </tr>
    <tr aria-label="agent info row" class="tbodyInfo" v-if="agentOpened.includes(order.ordNum) && role !== 'agent'">
      <td>{{ agentInfo.agentName }}</td>
      <td>{{ agentInfo.workingArea }}</td>
      <td>{{ agentInfo.phoneNo }}</td>
      <td>{{ agentInfo.country }}</td>
    </tr>
    <tr class="theadInfo" v-if="customerOpened.includes(order.ordNum) && role !== 'customer' ">
      <th>Customer Name</th>
      <th>Customer City</th>
      <th>Working Area</th>
      <th class="otherInfo">Country</th>
      <th>Phone Number</th>
    </tr>
    <tr class="tbodyInfo" v-if="customerOpened.includes(order.ordNum) && role !== 'customer' ">
      <td>{{ customerInfo.custName }}</td>
      <td>{{ customerInfo.custCity }}</td>
      <td>{{ customerInfo.workingArea }}</td>
      <td class="otherInfo">{{ customerInfo.custCountry }}</td>
      <td>{{ customerInfo.phoneNo }}</td>
    </tr>
    </tbody>
  </table>
</template>

<script>
import ServiceView from '../views/ServiceView.vue'
import {useQuery} from '@vue/apollo-composable'
import {
  AGENT_BY_AGENT_CODE,
  ALL_ORDERS,
  CUSTOMER_BY_CUST_CODE,
  ORDERS_BY_AGENT_CODE,
  ORDERS_BY_CUST_CODE
} from "./graphql/graphql_query";
import {computed} from "vue";
import {DELETE_ORDER} from "./graphql/graphql_mutation";

export default {
  name: "Orders",
  components: {
    'service': ServiceView
  },
  data() {
    return {
      role: localStorage.getItem('userRole'),
      currentSort: 'ordNum',
      currentSortDir: 'asc',
      agentOpened: [],
      customerOpened: [],
      agentInfo: [],
      customerInfo: []
    };
  },
  setup() {
    let code = localStorage.getItem("userCode");
    let role = localStorage.getItem("userRole");
    let query = '';
    let vars = '';
    if (role === 'customer') {
      query = ORDERS_BY_CUST_CODE;
      vars = {
        custCode: code
      };
    } else if (role === 'agent') {
      query = ORDERS_BY_AGENT_CODE;
      vars = {
        agentCode: code
      };
    } else if (role === 'admin') {
      query = ALL_ORDERS;
      vars = '';
    } else {
      console.log("Error: wrong user role")
    }
    const {result: res, loading, error} = useQuery(query, vars);
    //const order = useResult(res, [], res.allOrders);
    let orders = []
    if (role === 'customer') {
      orders = computed(() => res.value?.ordersByCustCode ?? [])
    } else if (role === 'agent') {
      orders = computed(() => res.value?.ordersByAgentCode ?? [])
    } else if (role === 'admin') {
      orders = computed(() => res.value?.allOrders ?? [])
    } else {
      console.log("Error: failed fetch")
    }

    return {
      orders,
      loading,
      error
    }
  },
  computed: {
    sortedOrders() {
      let sortedOrders = [...this.orders]
      return sortedOrders.sort((a, b) => {
        let modifier = 1;
        if (this.currentSortDir === 'desc') modifier = -1;
        if (this.currentSort === "customer") {
          if (a[this.currentSort]["custCode"] < b[this.currentSort]["custCode"]) return -1 * modifier;
          if (a[this.currentSort]["custCode"] > b[this.currentSort]["custCode"]) return modifier;
        } else if (this.currentSort === "agent") {
          if (a[this.currentSort]["agentCode"] < b[this.currentSort]["agentCode"]) return -1 * modifier;
          if (a[this.currentSort]["agentCode"] > b[this.currentSort]["agentCode"]) return modifier;
        } else {
          if (a[this.currentSort] < b[this.currentSort]) return -1 * modifier;
          if (a[this.currentSort] > b[this.currentSort]) return modifier;
        }
        return 0;
      });
    }
  },
  methods: {
    sort(s) {
      if (s === this.currentSort) {
        this.currentSortDir = this.currentSortDir === 'asc' ? 'desc' : 'asc';
      }
      this.currentSort = s;
    },
    agentToggle(id, orderNumber) {

      this.$apollo.query({
        query: AGENT_BY_AGENT_CODE,
        variables: {agentCode: id}
      }).then(res => {
        this.agentInfo = res.data.agentByAgentCode
      })

      const index = this.agentOpened.indexOf(orderNumber);
      if (index > -1) {
        //chiudi
        this.agentOpened.splice(index, 1)
      } else {
        //apri
        this.agentOpened =  []
        this.agentOpened.push(orderNumber)
      }
    },
    customerToggle(id, orderNumber) {

      this.$apollo.query({
        query: CUSTOMER_BY_CUST_CODE,
        variables: {custCode: id}
      }).then(res => {
        this.customerInfo = res.data.customerByCustCode
      })

      const index = this.customerOpened.indexOf(orderNumber);
      if (index > -1) {
        this.customerOpened.splice(index, 1)
      } else {
        this.customerOpened =  []
        this.customerOpened.push(orderNumber)
      }
    },
    allOrders() {
      this.$apollo.query({
        query: ALL_ORDERS
      }).then(res => {
        console.log("Fetch all orders");
      })
    },
    deleteOrder(ordNum) {
      this.$apollo.mutate({
        mutation: DELETE_ORDER,
        variables: {
          ordNum: ordNum
        }
      }).then(res => {
        console.log("Delete complete");
        document.location.reload()
        //this.sort(this.currentSort)
      });
    },
    editOrder(ordNum) {

      this.$router.push({
        name: "updateOrderPage",
        params: {ordNum}
      });

    }
  },
}
</script>

<style scoped>
@import "@/assets/table.css";

.create {
  margin: 0;
}

</style>