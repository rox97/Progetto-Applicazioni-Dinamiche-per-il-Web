<template>

    <div>
      <h2>Update Order Data</h2>
      <br>
      <div class="form-group">
        <label for="ordNum"><b>Order number</b></label>
        <input id="ordNum" type="text" disabled placeholder="ordNum" :value="orderData.ordNum" ref="ordNum">
      </div>
      <br>
      <br>
      <div class="form-group">
        <label for="ordAmount"><b>Order amount</b></label>
        <input id="ordAmount" type="text" placeholder="ordAmount" :value="orderData.ordAmount" ref="ordAmount">
      </div>
      <br>
      <br>
      <div class="form-group">
        <label for="advanceAmount"><b>Advance amount</b></label>
        <input id="advanceAmount" type="text" placeholder="advanceAmount" :value="orderData.advanceAmount" ref="advanceAmount">
      </div>
      <br>
      <br>
      <div class="form-group">
        <label for="ordDate"><b>Order date</b></label>
        <input id="ordDate" type="date" placeholder="ordDate" :value="orderData.ordDate" ref="ordDate">
      </div>
      <br>
      <br>
      <div class="form-group">
        <label for="ordDescription"><b>Order description</b></label>
        <input id="ordDescription" placeholder="ordDescription" type="text" :value="orderData.ordDescription" ref="ordDescription">
      </div>
      <br>
      <p v-if="msg">{{msg}}</p>
    </div>
<nav>
    <button @click="updateOrder(orderData)">Update</button>
    <RouterLink to="/Orders">
      <button>Back</button>
    </RouterLink>
  </nav>
</template>

<script>
import {useQuery} from '@vue/apollo-composable'
import {ORDER_BY_ORD_NUM} from "./graphql/graphql_query";
import {computed} from "vue";
import {useRoute} from "vue-router";
import {UPDATE_ORDER} from "./graphql/graphql_mutation";

export default {
  name: "updateOrder",
  data() {
    return {
      msg: '',
    }
  },
  setup() {
    const route = useRoute()
    let data = route.params.data;

    console.log("data is", data);

    const {result} = useQuery(ORDER_BY_ORD_NUM, {ordNum: data})
    const orderData = computed(() => result.value?.orderByOrdNum ?? [])

    return {
      result,
      orderData
    }
  },
  methods: {
    updateOrder(orderData) {

      const inputOrder = {
        "ordNum": orderData.ordNum,
        "ordAmount": this.$refs.ordAmount.value,
        "advanceAmount": this.$refs.advanceAmount.value,
        "ordDate": this.$refs.ordDate.value,
        "ordDescription": this.$refs.ordDescription.value
      }

      this.$apollo.mutate({
        mutation: UPDATE_ORDER,
        variables: {
          input:
          inputOrder
        },
      }).then(async ({data}) => {
        if (data) {
          console.log("Update Completato")
          window.location.href = "/orders"
        }
      })

    }
  }
};

</script>

<style scoped>
@import "@/assets/form.css";

</style>