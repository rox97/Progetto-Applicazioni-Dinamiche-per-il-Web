<template>
  <div class="createOrder" >
    <h1>Create Page</h1>
  </div>
  <div class="container">
    <div class="form-group">
      <label for="ordNum"><b>Order Number</b></label>
      <input id="ordNum" type="text" ref="ordNum" placeholder="Enter order number" name="ordNum"><br>
    </div>
    <div class="form-group">
      <label for="ordAmount"><b>ordAmount</b></label>
      <input id="ordAmount" type="text" ref="ordAmount" placeholder="Enter ordAmount" name="ordAmount"><br>
    </div>
    <div class="form-group">
      <label for="advanceAmount"><b>advanceAmount</b></label>
      <input id="advanceAmount" type="text" ref="advanceAmount" placeholder="Enter advanceAmount" name="advanceAmount"><br>
    </div>
    <div class="form-group">
      <label for="ordDate"><b>ordDate</b></label>
      <input id="ordDate" type="date" ref="ordDate" placeholder="Enter ordDate" name="ordDate"><br>
    </div>
    <div class="form-group">
      <label for="ordDescription"><b>ordDescription</b></label>
      <input id="ordDescription" type="text" ref="ordDescription" placeholder="Enter ordDescription" name="ordDescription"><br>
    </div>
    <div class="form-group">
      <label><b>custCode</b></label>
      <!--<input id="custCode" type="select" ref="custCode" placeholder="Enter custCode" name="custCode"><br>-->
      <select id="custCode" ref="custCode" >
        <option v-for="customer in customers">{{ customer.custCode }}</option>
      </select>
    </div>
    <p>{{msg}}</p>
    <button @click="create">Create</button>
    <RouterLink to="/orders">Back</RouterLink>
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
      msg: "",
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
          this.msg = "L'ordine esiste già";
          console.log("Order already exists");
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
              console.log("Create Completato")
              window.location.href = "/orders";
              /*this.$router.push({
                name: "ordersPage",
              });*/
            }
            else{
              //ERRORE
            }
          });
        }
      });
    }
  }
}
</script>

<style scoped>

h1 {
  text-align: center;
  color: white;
}

/* Full-width inputs */
input[type=text], input[type=password], input[type=number], input[type=date] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

/* Set a style for all buttons */

input[type=submit] {
  background-color: #00BD7EFF;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}

button {
  background-color: #00BD7EFF;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}

a{
  background-color: #00BD7EFF;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}

/* Add a hover effect for buttons */
button:hover {
  opacity: 0.8;
}
a:hover{
  opacity: 0.8;
}


/* Add padding to containers */
.container {
  padding: 16px;
}

@media screen and (max-width: 1024px) {

}
</style>