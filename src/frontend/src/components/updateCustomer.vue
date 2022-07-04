<template>


    <div>
      <h2>Update Customer Data</h2>
      <br>
      <input class="form-control" type="text" disabled placeholder="custCode" :value="custData.custCode" ref="custCode">
      <br>
      <br>
      <input class="form-control" type="text" placeholder="custName" :value="custData.custName" ref="custName">
      <br>
      <br>
      <input class="form-control" type="text" placeholder="custCity" :value="custData.custCity" ref="custCity">
      <br>
      <br>
      <input class="form-control" type="text" placeholder="workingArea" :value="custData.workingArea" ref="workingArea">
      <br>
      <br>
      <input class="form-control" placeholder="custCountry" type="text" :value="custData.custCountry" ref="custCountry">
      <br>
      <br>
      <input class="form-control" placeholder="grade" type="text" :value="custData.grade" ref="grade">
      <br>
      <br>
      <input class="form-control" placeholder="openingAmt" type="text" :value="custData.openingAmt" ref="openingAmt">
      <br>
      <br>
      <input class="form-control" placeholder="receiveAmt" type="text" :value="custData.receiveAmt" ref="receiveAmt">
      <br>
      <br>
      <input class="form-control" placeholder="paymentAmt" type="text" :value="custData.paymentAmt" ref="paymentAmt">
      <br>
      <br>
      <input class="form-control" placeholder="outstandingAmt" type="text" :value="custData.outstandingAmt" ref="outstandingAmt">
      <br>
      <br>
      <input class="form-control" placeholder="phoneNo" type="text" :value="custData.phoneNo" ref="phoneNo">
      <br>
      <p v-if="msg">{{msg}}</p>
    </div>
<nav>
    <button @click="updateCustomer(custData)">Update</button>
    <RouterLink to="/customers"><button>Back</button></RouterLink>
</nav>
</template>

<script>
import {useQuery} from '@vue/apollo-composable'
import {CUSTOMER_BY_CUST_CODE} from "./graphql/graphql_query";
import {computed} from "vue";
import {UPDATE_CUSTOMER} from "./graphql/graphql_mutation";
import {useRoute} from "vue-router";

export default {
  name:"updateCustomer",
  data(){
    return {
      msg:'',
    }
  },
  setup(){
    const route = useRoute()
    let data = route.params.data;

    console.log("data is", data);

    const {result} = useQuery(CUSTOMER_BY_CUST_CODE, {custCode: data})
    const custData = computed(() => result.value?.customerByCustCode ?? [])

    return {
      result,
      custData
    }
  },
  methods: {
    updateCustomer(custData){

      const inputCustomer={
        "custCode":custData.custCode,
        "custName":this.$refs.custName.value,
        "custCity":this.$refs.custCity.value,
        "workingArea":this.$refs.workingArea.value,
        "custCountry":this.$refs.custCountry.value,
        "grade":this.$refs.grade.value,
        "openingAmt":this.$refs.openingAmt.value,
        "receiveAmt":this.$refs.receiveAmt.value,
        "paymentAmt":this.$refs.paymentAmt.value,
        "outstandingAmt":this.$refs.outstandingAmt.value,
        "phoneNo":this.$refs.phoneNo.value
      }

      this.$apollo.mutate({
        mutation: UPDATE_CUSTOMER,
        variables: {
          input:
          inputCustomer
        },
      }).then(async ({data})=> {
        if (data){
          console.log("Update Completato")
          window.location.href = "/customers"
        }

      })
    }
  }
};
</script>

<style scoped>

</style>