<template>
  <div class="createOrder" >
    <h1>Create Order</h1>
  </div>
  <div class="container">
    <div class="form-group">
      <label for="ordNum"><b>Order Number</b></label>
      <input autofocus id="ordNum" type="text" ref="ordNum" placeholder="Enter order number" name="ordNum"><br>
    </div>
    <div class="form-group">
      <label for="ordAmount"><b>Order Amount</b></label>
      <input id="ordAmount" type="text" ref="ordAmount" placeholder="Enter ordAmount" name="ordAmount"><br>
    </div>
    <div class="form-group">
      <label for="advanceAmount"><b>Advance Amount</b></label>
      <input id="advanceAmount" type="text" ref="advanceAmount" placeholder="Enter advanceAmount" name="advanceAmount"><br>
    </div>
    <div class="form-group">
      <label for="ordDate"><b>Order Date</b></label>
      <input id="ordDate" type="date" ref="ordDate" placeholder="Enter ordDate" name="ordDate"><br>
    </div>
    <div class="form-group">
      <label for="ordDescription"><b>Order Description</b></label>
      <input id="ordDescription" type="text" ref="ordDescription" placeholder="Enter ordDescription" name="ordDescription"><br>
    </div>
    <div class="form-group">
      <label for="custCode"><b>Customer Code </b></label>
      <select aria-label="custCode" ref="custCode" >
        <option id="custCode" v-for="customer in customers">{{ customer.custCode }}</option>
      </select>
    </div>
    <p>{{msg}}</p>
    <button @click="create">Create</button>
    <RouterLink to="/orders"><button>Back</button></RouterLink>
  </div>
</template>

<script>
import {CREATE_ORDER} from "./graphql/graphql_mutation";
import {ALL_CUSTOMERS, EXISTS_ORDER_BY_ORD_NUM} from "./graphql/graphql_query";
import {useQuery} from "@vue/apollo-composable";
import {computed} from "vue";

export default {
  name: "createOrder",
  data() {
    return {
      msg: ""
    }
  },
  setup() {
    const {result: res} = useQuery(ALL_CUSTOMERS);
    let customers = computed(() => res.value?.allCustomers ?? [])
    return {
      customers
    };
  },
  methods: {
    create() {
      this.$apollo.query({
        query: EXISTS_ORDER_BY_ORD_NUM,
        variables: {
          ordNum: this.$refs.ordNum.value
        }
      }).then(res => {
        if (res.data.existsOrderByOrdNum) {
          this.msg = "Order already exists";
        } else {
          const inputOrder = {
            "ordNum": this.$refs.ordNum.value,
            "ordAmount": this.$refs.ordAmount.value,
            "advanceAmount": this.$refs.advanceAmount.value,
            "ordDate": this.$refs.ordDate.value,
            "ordDescription": this.$refs.ordDescription.value,
            "agentCode": localStorage.getItem("userCode"),
            "custCode": this.$refs.custCode.value
          }
          this.$apollo.mutate({
            mutation: CREATE_ORDER,
            variables: {
              input: inputOrder
            },
          }).then(({data}) => {
            if (data) {
              console.log("Create Complete")
              window.location.href = "/orders";
              /*this.$router.push({
                name: "ordersPage",
              });*/
            }
            else{
              this.msg = "Error: failed creation"
            }
          });
        }
      });
    }
  }
}
</script>

<style scoped>
@import "@/assets/form.css";
</style>