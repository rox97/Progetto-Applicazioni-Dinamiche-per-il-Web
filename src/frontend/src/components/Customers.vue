<template>
  <table>
    <thead class='thead'>
  <tr>
    <th @click="sort('custCode')">Customer Code</th>
    <th @click="sort('custName')">Customer Name</th>
    <th class="otherInfo" @click="sort('custCity')">City</th>
    <th class="otherInfo" @click="sort('workingArea')">Working Area</th>
    <th class="otherInfo" @click="sort('custCountry')">Country</th>
   <!-- <th @click="sort('grade')">Grade</th>
    <th @click="sort('openingAmt')">Opening Amount</th>
    <th @click="sort('receiveAmt')">Receive Amount</th>
    <th @click="sort('paymentAmt')">Payment Amount</th>
    <th @click="sort('outstandingAmt')">Outstanding Amount</th>-->
    <th class="otherInfo" @click="sort('phoneNo')">Phone No</th>
    <th @click="sort('agent')">Agent Code</th>
    <th class="infoCol">Edit</th>
  </tr>
  </thead>
    <tbody v-for="customer in sortedCustomers" :key="customer.custCode">
  <tr class="table">
    <td>{{ customer.custCode }}</td>
    <td>{{ customer.custName}}</td>
    <td class="otherInfo">{{ customer.custCity}}</td>
    <td class="otherInfo">{{ customer.workingArea}}</td>
    <td class="otherInfo">{{ customer.custCountry }}</td>
    <!--<td>{{ customer.grade }}</td>
    <td>{{ customer.openingAmt }}</td>
    <td>{{ customer.receiveAmt }}</td>
    <td>{{ customer.paymentAmt }}</td>
    <td>{{ customer.outstandingAmt }}</td>-->
    <td class="otherInfo">{{ customer.phoneNo }}</td>
    <td><button @click="agentToggle(customer.agent.agentCode, customer.custCode)" :class="{ agentOpened: agentOpened.includes(customer.custCode) }">{{ customer.agent.agentCode }}</button></td>
    <td><button @click="editCustomer(customer.custCode)">Edit</button></td>
  </tr>
  <tr class="theadInfo" v-if="agentOpened.includes(customer.custCode)">
    <th>Agent Name</th>
    <th>Working Area</th>
    <th>Phone Number</th>
    <th>Agent Country</th>
  </tr>
  <tr class="tbodyInfo" v-if="agentOpened.includes(customer.custCode)">
    <td>{{ agentInfo.agentName }}</td>
    <td>{{ agentInfo.workingArea }}</td>
    <td>{{ agentInfo.phoneNo }}</td>
    <td>{{ agentInfo.country }}</td>
  </tr>
    </tbody>
</table>

</template>

<script>
import ServiceView from '../views/ServiceView.vue'
import {useQuery} from '@vue/apollo-composable'
import {AGENT_BY_AGENT_CODE, ALL_CUSTOMERS} from "./graphql/graphql_query";
import {computed} from "vue";

export default {
  name: "Customers",
  components:{
    'service':ServiceView
  },

  data() {
    return {
      role : localStorage.getItem('userRole'),
      currentSort:'ordNum',
      currentSortDir:'asc',
      agentOpened: [],
      agentInfo: []
    };
  },

  setup() {
    const {result: res, loading, error} = useQuery(ALL_CUSTOMERS);
    //const orders = useResult(test, [], test.allOrders);
    let customers = '';
    customers = computed(() => res.value?.allCustomers ?? []);

    return {
      customers,
      loading,
      error
    }
  },
  computed:{
    sortedCustomers:function() {
      let copy = [...this.customers]
      return copy.sort((a,b) => {
        let modifier = 1;
        if(this.currentSortDir === 'desc') modifier = -1;
        if (this.currentSort === "agent"){
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
    agentToggle(id, customerCode) {
      this.$apollo.query({
        query: AGENT_BY_AGENT_CODE,
        variables:{ agentCode: id}}).then(res => {
        this.agentInfo = res.data.agentByAgentCode})

      const index = this.agentOpened.indexOf(customerCode);
      if (index > -1) {
        this.agentOpened.splice(index, 1)
      } else {
        this.agentOpened =  []
        this.agentOpened.push(customerCode)
      }
    },
    editCustomer(custCode) {

      this.$router.push({
        name: "updateCustomerPage",
        params:  {custCode}
      });
    }
  }
}
</script>

<style scoped>
@import "@/assets/table.css";

</style>