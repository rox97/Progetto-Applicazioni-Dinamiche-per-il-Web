<template>


    <div>
      <h2>Update Customer Data</h2>
      <br>
      <div class="form-group">
        <label for="custCode"><b>Customer code</b></label>
        <input id="custCode" type="text" readonly  placeholder="custCode" :value="custData.custCode">
      </div>
      <br>
      <br>
      <div class="form-group">
        <label for="custName"><b>Customer name</b></label>
        <input autofocus id="custName" type="text" placeholder="custName" :value="custData.custName" ref="custName">
      </div>
      <br>
      <br>
      <div class="form-group">
        <label for="custCity"><b>Customer city</b></label>
        <input id="custCity" type="text" placeholder="custCity" :value="custData.custCity" ref="custCity">
      </div>
      <br>
      <br>
      <div class="form-group">
        <label for="workingArea"><b>Working area</b></label>
        <input id="workingArea" type="text" placeholder="workingArea" :value="custData.workingArea" ref="workingArea">
      </div>
      <br>
      <br>
      <div class="form-group">
        <label for="custCountry"><b>Customer country</b></label>
        <input id="custCountry" placeholder="custCountry" type="text" :value="custData.custCountry" ref="custCountry">
      </div>
      <br>
      <br>
      <div class="form-group">
        <label for="grade"><b>Grade</b></label>
        <input id="grade" placeholder="grade" type="text" :value="custData.grade" ref="grade">
      </div>
      <br>
      <br>
      <div class="form-group">
        <label for="openingAmt"><b>Opening amount</b></label>
        <input id="openingAmt" placeholder="openingAmt" type="text" :value="custData.openingAmt" ref="openingAmt">
      </div>
      <br>
      <br>
      <div class="form-group">
        <label for="receiveAmt"><b>Receive amount</b></label>
        <input id="receiveAmt" placeholder="receiveAmt" type="text" :value="custData.receiveAmt" ref="receiveAmt">
      </div>
      <br>
      <br>
      <div class="form-group">
        <label for="paymentAmt"><b>Payment amount </b></label>
        <input id="paymentAmt" placeholder="paymentAmt" type="text" :value="custData.paymentAmt" ref="paymentAmt">
      </div>
      <br>
      <br>
      <div class="form-group">
        <label for="outstandingAmt"><b>Outstanding amount</b></label>
      <input id="outstandingAmt" placeholder="outstandingAmt" type="text" :value="custData.outstandingAmt" ref="outstandingAmt">
      </div>
      <br>
      <br>
      <div class="form-group">
        <label for="phoneNo"><b>Phone number</b></label>
        <input id="phoneNo" placeholder="phoneNo" type="text" :value="custData.phoneNo" ref="phoneNo">
      </div>
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
      msg:''
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
          input: inputCustomer
        },
      }).then(async ({data})=> {
        if (data){
          console.log("Update Complete")
          window.location.href = "/customers"
        }
        else{
          this.msg = "Error: failed update"
        }

      })
    }
  }
};
</script>

<style scoped>
@import "@/assets/form.css";

</style>