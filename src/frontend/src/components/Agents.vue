<template>
  <service></service>
  <tr class="table" v-for="agent in agents" :key="agent.agentCode">
    <td>{{ agent.agentCode }}</td>
    <td>{{ agent. agentName}}</td>
    <td>{{ agent.workingArea}}</td>
    <td>{{ agent.commission}}</td>
    <td>{{ agent.phoneNo }}</td>
    <td>{{ agent.country }}</td>
  </tr>

</template>

<script>
import ServiceView from '../views/ServiceView.vue'
import {useQuery} from '@vue/apollo-composable'
import {ALL_AGENTS} from "./graphql/graphql_query";
import {computed} from "vue";

export default {
  name: "Agents",
  components:{
    'service':ServiceView
  },

  data() {
    return {
      role : localStorage.getItem('userRole')
    };
  },

  setup() {
    const {result: res, loading, error} = useQuery(ALL_AGENTS);
    //const orders = useResult(test, [], test.allOrders);
    let agents = '';
    agents = computed(() => res.value?.allAgents ?? [])

    return {
      agents,
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