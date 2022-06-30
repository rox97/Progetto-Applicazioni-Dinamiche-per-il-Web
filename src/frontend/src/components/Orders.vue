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
    <!--<td><div id="infoDiv"  style="display:none;" class="answer_list" > WELCOME</div></td>-->
  </tr>
    <tr><td><div id="infoDiv"  style="display:none;" class="answer_list" > WELCOME</div></td></tr>
  </table>




</template>

<script>
import ServiceView from '../views/ServiceView.vue'
import {useMutation, useQuery, useResult} from '@vue/apollo-composable'
import {ALL_ORDERS, ORDERS_BY_AGENT_CODE, ORDERS_BY_CUST_CODE} from "./graphql/graphql_query";
import {gql} from "graphql-tag";
import {computed, onMounted} from "vue";

export default {
  name: "Orders",
  components:{
    'service':ServiceView
  },
  data() {
    return {
      role : localStorage.getItem('userRole'),
      currentSort:'ordNum',
      currentSortDir:'asc'
    };
  },
  setup() {
    /*let currentSort='ordNum'
    let currentSortDir='asc'*/
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
    showDiv(code, ordNum) {
      console.log(code)
      //let table = document.getElementById("my-table");
      //let row = table.insertRow;
      document.getElementById('infoDiv').style.display = "block";
    },
    allOrders() {
      this.$apollo.query({
        query: ALL_ORDERS}).then(res => {
        console.log(res);
      })
    },
    deleteOrder(){
     this.$apollo.mutate({
        mutation: gql`
          mutation {
            deleteOrder(ordNum: 200100)
          }
        `
      }).then(res => {
        console.log(res);
      })
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