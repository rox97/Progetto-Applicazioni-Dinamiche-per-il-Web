<template>

    <div>

      <h2>Update Agent Data</h2>

      <input class="form-control" type="text" disabled placeholder="agentCode" :value="agentData.agentCode" ref="agentCode">
      <br>
      <input class="form-control" type="text" placeholder="agentName" :value="agentData.agentName" ref="agentName">
      <br>
      <br><input class="form-control" type="text" placeholder="workingArea" :value="agentData.workingArea" ref="workingArea">
      <br>
      <br><input class="form-control" type="text" placeholder="commission" :value="agentData.commission" ref="commission">
      <br>
      <input class="form-control" placeholder="phone No" type="text" :value="agentData.phoneNo" ref="phoneNo">
      <br>
      <input class="form-control" placeholder="country" type="text" :value="agentData.country" ref="country">
      <br>
      <p v-if="msg">{{msg}}</p>
      <br>
      <br>
      <br>
    </div>

    <button type="button" class="btn btn-circle-bottom" v-on:click="updateAgent(agentData)">Update </button>

</template>

<script>
import {useQuery} from '@vue/apollo-composable'
import {AGENT_BY_AGENT_CODE} from "./graphql/graphql_query";
import {computed} from "vue";
import {UPDATE_AGENT} from "./graphql/graphql_mutation";
import {useRoute} from "vue-router";

export default {
  name:"updateAgent",
  data(){
    return {
      msg:'',
    }
  },
  setup(){
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
    /*agentData(){
      let data = this.$route.params.data;
      console.log(data);
      const {result} = useQuery(AGENT_BY_AGENT_CODE, {agentCode: data})
      const agentData = computed(() => result.value?.agentByAgentCode ?? [])

      return {
        result,
        agentData
      }
    },*/
    updateAgent(agentData){

      const inputAgent={
        "agentCode":agentData.agentCode,"workingArea":this.$refs.workingArea.value,"commission":this.$refs.commission.value,"phoneNo":this.$refs.phoneNo.value,"country":this.$refs.country.value
      }

      this.$apollo.mutate({
        mutation: UPDATE_AGENT,
        variables: {
          input:
          inputAgent


        },
      }).then(async ({data})=> {
        if (data){
          this.msg="Update Completato"
        }

      })
      document.location.reload()
    }

  }



};

</script>

<style scoped>

</style>