<template>
  <service></service>
  <div class="orders">
    <nav role="navigation" aria-label="navigation">
      <RouterLink id="create_order" to="/createOrder" v-if="role === 'agent'">Crea Ordine</RouterLink>
    </nav>
  </div>
  <tr class="table" v-for="order in orders" :key="order.ordNum">
    <td>{{ order.ordNum }}</td>
    <td>{{ order.ordAmount }}</td>
    <td>{{ order.advanceAmount }}</td>
    <td>{{ order.ordDate }}</td>
    <td>{{ order.ordDescription }}</td>
    <td v-if="role === 'admin' || role === 'customer'">{{ order.agent.agentCode }}</td>
    <td v-if="role === 'admin' || role === 'agent'">{{ order.customer.custCode }}</td>
  </tr>

</template>

<script>
import ServiceView from '../views/ServiceView.vue'
import {useMutation, useQuery} from '@vue/apollo-composable'
import {ALL_ORDERS, ORDERS_BY_AGENT_CODE, ORDERS_BY_CUST_CODE} from "./graphql/graphql_query";
import {gql} from "graphql-tag";
import {computed} from "vue";

export default {
  name: "Orders",
  components:{
    'service':ServiceView
  },

  data() {
    return {
      role : localStorage.getItem('userRole')
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
    //const orders = useResult(test, [], test.allOrders);
    let orders = '';
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
  methods:{
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
  }
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