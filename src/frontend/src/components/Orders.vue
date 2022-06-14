<script setup>
import { useQuery} from '@vue/apollo-composable'
import {ALL_ORDERS, ORDERS_BY_AGENT_CODE, ORDERS_BY_CUST_CODE} from "./graphql/graphql_query";
localStorage.setItem('userRole' , 'customer');
localStorage.setItem("userCode" , "C00022");
let code = localStorage.getItem("userCode");
let role = 'customer';
let res= ''
let vars= ''
switch (role){
  case 'customer':
    res = ORDERS_BY_CUST_CODE;
    vars = {
      custCode: code
    };
    break;
  case 'agent':
    res = ORDERS_BY_AGENT_CODE;
    vars = {
      agentCode: code
    };
    break;
  case 'admin':
    res = ALL_ORDERS;
    vars= '';
    break;
  default:
    res =ALL_ORDERS;
    vars= '';
    break;
}
const {result} = useQuery(res,vars);
const res1 = JSON.stringify(result);
</script>

<template>

  <div >Risultato query: {{result}}</div>

  <div>Risultato stringify query: {{ res1 }}</div>

  <div>Risultato script query: {{ test }}</div>



</template>

<script>
import {ALL_ORDERS, ORDERS_BY_AGENT_CODE, ORDERS_BY_CUST_CODE} from "./graphql/graphql_query";


export default {
  name: "Orders",
  setup(){
  },
  apollo:{
    test:{
      query: ALL_ORDERS,
    }
  }
}
</script>

<style scoped>

</style>