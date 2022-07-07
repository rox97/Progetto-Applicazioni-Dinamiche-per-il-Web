<template>
  <service></service>
  <table>
    <thead class='thead'>
    <tr>
      <th @click="sort('agentCode')">Agent Code</th>
      <th @click="sort('agentName')">Agent Name</th>
      <th class="otherInfo" @click="sort('workingArea')">Working Area</th>
      <th class="otherInfo" @click="sort('commission')">Commission</th>
      <th @click="sort('phoneNo')">Phone Number</th>
      <th class="otherInfo" @click="sort('country')">Agent Country</th>
      <th class="infoCol">Edit</th>
    </tr>
    </thead>
    <tbody v-for="agent in sortedAgents" :key="agent.agentCode">
  <tr class="table">
    <td>{{ agent.agentCode }}</td>
    <td>{{ agent.agentName}}</td>
    <td class="otherInfo">{{ agent.workingArea}}</td>
    <td class="otherInfo">{{ agent.commission}}</td>
    <td>{{ agent.phoneNo }}</td>
    <td class="otherInfo">{{ agent.country }}</td>
    <td><button @click="editAgent(agent.agentCode)">Edit</button></td>
  </tr>
    </tbody>
  </table>
  <router-view />
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
      role : localStorage.getItem('userRole'),
      currentSort:'ordNum',
      currentSortDir:'asc'
    };
  },

  setup() {
    const {result: res, loading, error} = useQuery(ALL_AGENTS)
    //const orders = useResult(test, [], test.allOrders);
    let agents = ''
    agents = computed(() => res.value?.allAgents ?? [])

    return {
      agents,
      loading,
      error
    }
  },
  computed:{
    sortedAgents:function() {
      let copy = [...this.agents]
      return copy.sort((a,b) => {
        let modifier = 1;
        if(this.currentSortDir === 'desc') modifier = -1;
        if (a[this.currentSort] < b[this.currentSort]) return -1 * modifier;
        if (a[this.currentSort] > b[this.currentSort]) return modifier;
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
    editAgent(agentCode){

      this.$router.push({
        name: "updateAgentPage",
        params:  {agentCode}
      });

    }
  }
}
</script>

<style scoped>
@import "@/assets/table.css";

</style>