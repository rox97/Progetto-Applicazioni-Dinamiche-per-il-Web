<template>

  <button @click="deleteOrder">Delete</button>
  <button @click="allOrders">Orders</button>
  <div >Risultato query: {{res}}</div>
  <div >Risultato query: {{orders}}</div>
  <tr v-for="order in orders">
    <td>{{ order.ordNum }}</td>
    <td>{{ order.ordAmount }}</td>

  </tr>

</template>

<script>
import {useMutation, useQuery} from '@vue/apollo-composable'
import {ALL_ORDERS, ORDERS_BY_AGENT_CODE, ORDERS_BY_CUST_CODE} from "./graphql/graphql_query";
import {gql} from "graphql-tag";
import {apolloProvider} from "../main";
import {computed, watchEffect} from "vue";

export default {
  name: "Orders",

  setup() {
    let code = localStorage.getItem("userCode");
    let role = localStorage.getItem("userRole");
    let query = ''
    let vars = ''
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
    //let orders = computed(() => res.value ?? [])
    console.log(res);
    return {
      res,
      orders,
      loading,
      error
    }
  },
  methods:{
    allOrders() {
      this.$apollo.query({
        query: gql`
        query {
          allOrders {
            ordNum
            ordAmount
          }
        }
        `
      }).then(res => {
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

</style>