<template>

  <div>
    <h2>Update Agent Data</h2>
    <br>
    <input class="form-control" type="text" disabled placeholder="agentCode" :value="agentData.agentCode"
           ref="agentCode">
    <br>
    <br>
    <input class="form-control" type="text" placeholder="agentName" :value="agentData.agentName" ref="agentName">
    <br>
    <br>
    <input class="form-control" type="text" placeholder="workingArea" :value="agentData.workingArea" ref="workingArea">
    <br>
    <br>
    <input class="form-control" type="text" placeholder="commission" :value="agentData.commission" ref="commission">
    <br>
    <br>
    <input class="form-control" placeholder="phone No" type="text" :value="agentData.phoneNo" ref="phoneNo">
    <br>
    <br>
    <input class="form-control" placeholder="country" type="text" :value="agentData.country" ref="country">
    <br>
    <p v-if="msg">{{ msg }}</p>
  </div>
  <nav>
    <button @click="updateAgent(agentData)">Update</button>
    <RouterLink to="/Agents"><button>Back</button></RouterLink>
  </nav>
</template>

<script>
import {useQuery} from '@vue/apollo-composable'
import {AGENT_BY_AGENT_CODE} from "./graphql/graphql_query";
import {computed} from "vue";
import {UPDATE_AGENT} from "./graphql/graphql_mutation";
import {useRoute} from "vue-router";

export default {
  name: "updateAgent",
  data() {
    return {
      msg: '',
    }
  },
  setup() {
    const route = useRoute()
    let data = route.params.data;

    console.log("data is", data);

    const {result} = useQuery(AGENT_BY_AGENT_CODE, {agentCode: data})
    const agentData = computed(() => result.value?.agentByAgentCode ?? [])

    return {
      result,
      agentData
    }
  },
  methods: {
    updateAgent(agentData) {

      const inputAgent = {
        "agentCode": agentData.agentCode,
        "agentName": this.$refs.agentName.value,
        "workingArea": this.$refs.workingArea.value,
        "commission": this.$refs.commission.value,
        "phoneNo": this.$refs.phoneNo.value,
        "country": this.$refs.country.value
      }

      this.$apollo.mutate({
        mutation: UPDATE_AGENT,
        variables: {
          input:
          inputAgent
        },
      }).then(async ({data}) => {
        if (data) {
          console.log("Update Completato")
          window.location.href = "/agents"
        }
      })
    }
  }
};

</script>

<style scoped>
@import "@/assets/form.css";

</style>