<template>
  <service></service>
  <table border="1">
    <thead>
  <tr>
    <th>Customer Code</th>
    <th>Customer Name</th>
    <th>City</th>
    <th>Working Area</th>
    <th>Country</th>
    <th>Grade</th>
    <th>Opening Amount</th>
    <th>Receive Amount</th>
    <th>Payment Amount</th>
    <th>Outstanding Amount</th>
    <th>Phone No</th>
    <th>Agent Code</th>
  </tr>
  </thead>
    <tbody>
  <tr class="table" v-for="customer in customers" :key="customer.custCode">
    <td>{{ customer.custCode }}</td>
    <td>{{ customer.custName}}</td>
    <td>{{ customer.custCity}}</td>
    <td>{{ customer.workingArea}}</td>
    <td>{{ customer.custCountry }}</td>
    <td>{{ customer.grade }}</td>
    <td>{{ customer.openingAmt }}</td>
    <td>{{ customer.receiveAmt }}</td>
    <td>{{ customer.paymentAmt }}</td>
    <td>{{ customer.outstandingAmt }}</td>
    <td>{{ customer.phoneNo }}</td>
    <td>{{ customer.agent.agentCode }}</td>
  </tr>
    </tbody>
</table>

</template>

<script>
import ServiceView from '../views/ServiceView.vue'
import {useQuery} from '@vue/apollo-composable'
import {ALL_CUSTOMERS} from "./graphql/graphql_query";
import {computed} from "vue";

export default {
  name: "Customers",
  components:{
    'service':ServiceView
  },

  data() {
    return {
      role : localStorage.getItem('userRole')
    };
  },

  setup() {
    const {result: res, loading, error} = useQuery(ALL_CUSTOMERS);
    //const orders = useResult(test, [], test.allOrders);
    let customers = '';
    customers = computed(() => res.value?.allCustomers ?? [])

    return {
      customers,
      loading,
      error
    }
  },
  methods:{
  }
}
</script>

<style scoped>

</style>