<template>
  <service></service>
  <div class="orders">
    <nav role="navigation" aria-label="navigation">
      <RouterLink id="create_order" to="/createOrder" v-if="role === 'agent'">Crea Ordine</RouterLink>
    </nav>
  </div>
  <table id="my-table">
    <thead>
    <tr>
      <th @click="sort('ordNum')">Order Number</th>
      <th @click="sort('ordAmount')">Order Amount</th>
      <th @click="sort('advanceAmount')">Advance Amount</th>
      <th @click="sort('ordDate')">Order Date</th>
      <th @click="sort('ordDescription')">Order Description</th>
      <th @click="sort('agent')" v-if="role === 'admin' || role === 'customer'">Agent Code</th>
      <th @click="sort('customer')" v-if="role === 'admin' || role === 'agent'">Customer Code</th>
      <th v-if="role === 'agent'">Delete</th>
    </tr>
    </thead>
  <tr class="table" v-for="order in sortedOrders" :key="order.ordNum">
    <td>{{ order.ordNum }}</td>
    <td>{{ order.ordAmount }}</td>
    <td>{{ order.advanceAmount }}</td>
    <td>{{ order.ordDate }}</td>
    <td>{{ order.ordDescription }}</td>
    <td v-if="role === 'admin' || role === 'customer'"><button name="answer" @click="showDiv(order.agent.agentCode, order.ordNum)">{{ order.agent.agentCode }}</button></td>
    <td v-if="role === 'admin' || role === 'agent'"><button name="answer" @click="showDiv(order.customer.custCode)">{{ order.customer.custCode }}</button></td>
    <td v-if="role === 'agent'"><button @click="deleteOrder(order.ordNum)">Delete</button></td>
    <!--<td><div id="infoDiv"  style="display:none;" class="answer_list" > WELCOME</div></td>-->
  </tr>
    <tbody v-for="order in sortedOrders" :key="order.ordNum">
    <tr class="table" >
      <td>{{ order.ordNum }}</td>
      <td>{{ order.ordAmount }}</td>
      <td>{{ order.advanceAmount }}</td>
      <td>{{ order.ordDate }}</td>
      <td>{{ order.ordDescription }}</td>
      <td v-if="role === 'admin' || role === 'customer'"><button name="answer" @click="agentToggle(order.agent.agentCode, order.ordNum)" :class="{ agentOpened: agentOpened.includes(order.ordNum) }">{{ order.agent.agentCode }}</button></td>
      <td v-if="role === 'admin' || role === 'agent'"><button name="answer" @click="customerToggle(order.customer.custCode, order.ordNum)" :class="{ customerOpened: customerOpened.includes(order.ordNum) }">{{ order.customer.custCode }}</button></td>
      <td v-if="role === 'admin'"><button>Edit</button></td>
    </tr>
    <tr v-if="customerOpened.includes(order.ordNum) && role !== 'customer' ">
      <th>Customer Name</th>
      <th>Customer City</th>
      <th>Working Area</th>
      <th>Customer Country</th>
      <th>Phone Number</th>
    </tr>
    <tr v-if="customerOpened.includes(order.ordNum) && role !== 'customer' ">
      <td>{{ customerInfo.custName }}</td>
      <td>{{ customerInfo.custCity }}</td>
      <td>{{ customerInfo.workingArea }}</td>
      <td>{{ customerInfo.custCountry }}</td>
      <td>{{ customerInfo.phoneNo }}</td>
    </tr>
    <tr v-if="agentOpened.includes(order.ordNum) && role !== 'agent'">
      <th>Agent Name</th>
      <th>Working Area</th>
      <th>Phone Number</th>
      <th>Agent Country</th>
    </tr>
    <tr v-if="agentOpened.includes(order.ordNum) && role !== 'agent'">
      <td>{{ agentInfo.agentName }}</td>
      <td>{{ agentInfo.workingArea }}</td>
      <td>{{ agentInfo.phoneNo }}</td>
      <td>{{ agentInfo.country }}</td>
    </tr>

    </tbody>
    <tr><td><div id="infoDiv"  style="display:none;" class="answer_list" > WELCOME</div></td></tr>
  </table>




</template>

<script>
import ServiceView from '../views/ServiceView.vue'
import {useMutation, useQuery, useResult} from '@vue/apollo-composable'
import {
  AGENT_BY_AGENT_CODE,
  ALL_ORDERS,
  CUSTOMER_BY_CUST_CODE,
  ORDERS_BY_AGENT_CODE,
  ORDERS_BY_CUST_CODE
} from "./graphql/graphql_query";
import {gql} from "graphql-tag";
import {computed, onMounted} from "vue";
import {DELETE_ORDER} from "./graphql/graphql_mutation";

export default {
  name: "Orders",
  components:{
    'service':ServiceView
  },
  data() {
    return {
      role : localStorage.getItem('userRole'),
      currentSort:'ordNum',
      currentSortDir:'asc',
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
      // ERRORE
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
      // ERRORE
    }

    return {
      orders,
      loading,
      error
    }
  },

  computed:{
    sortedOrders:function() {
      let copy = [...this.orders]
      return copy.sort((a,b) => {
        let modifier = 1;
        if(this.currentSortDir === 'desc') modifier = -1;
        if(this.currentSort === "customer"){
          if (a[this.currentSort]["custCode"] < b[this.currentSort]["custCode"]) return -1 * modifier;
          if (a[this.currentSort]["custCode"] > b[this.currentSort]["custCode"]) return modifier;
        }
        else if (this.currentSort === "agent"){
          if (a[this.currentSort]["agentCode"] < b[this.currentSort]["agentCode"]) return -1 * modifier;
          if (a[this.currentSort]["agentCode"] > b[this.currentSort]["agentCode"]) return modifier;
        }
        else {
          if (a[this.currentSort] < b[this.currentSort]) return -1 * modifier;
          if (a[this.currentSort] > b[this.currentSort]) return modifier;
        }
        return 0;
      });
    }
  },
  methods:{
    sort(s) {
      if(s === this.currentSort) {
        this.currentSortDir = this.currentSortDir==='asc'?'desc':'asc';
      }
      this.currentSort = s;
    },
    agentToggle(id, orderNumber) {
      this.$apollo.query({
        query: AGENT_BY_AGENT_CODE,
        variables:{ agentCode: id}}).then(res => {
        this.agentInfo = res.data.agentByAgentCode})

      const index = this.agentOpened.indexOf(orderNumber);
      if (index > -1) {
        this.agentOpened.splice(index, 1)
      } else {
        this.agentOpened.push(orderNumber)
      }
    },
    customerToggle(id, orderNumber) {
      this.$apollo.query({
        query: CUSTOMER_BY_CUST_CODE,
        variables:{ custCode: id}}).then(res => {
        this.customerInfo = res.data.customerByCustCode})

      const index = this.customerOpened.indexOf(orderNumber);
      if (index > -1) {
        this.customerOpened.splice(index, 1)
      } else {
        this.customerOpened.push(orderNumber)
      }
    },
    allOrders() {
      this.$apollo.query({
        query: ALL_ORDERS}).then(res => {
        console.log(res);
      })
    },

    deleteOrder(ordNum){
        this.$apollo.mutate({
        mutation: DELETE_ORDER,
        variables: {
          input:
          ordNum
          },

      }).then(res => {
        console.log(res);
      })
      document.location.reload()
    }



  },
}
</script>

<style scoped>

a,
.green {
  text-decoration: none;
  color: hsla(160, 100%, 37%, 1);
  transition: 0.4s;
}

@media (hover: hover) {
  a:hover {
    background-color: hsla(166, 100%, 37%, 0.2);
  }
}
@media (min-width: 1024px) {
nav {
  text-align: center;
  margin-left: -1rem;
  font-size: 1rem;

  padding: 1rem 0;
  margin-top: 1rem;
}
}
</style>