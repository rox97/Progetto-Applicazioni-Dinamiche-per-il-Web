<template>
  <service></service>
  <table>
    <thead class='thead'>
    <tr>
      <th @click="sort('agentCode')">Agent Code</th>
      <th @click="sort('agentName')">Agent Name</th>
      <th @click="sort('workingArea')">Working Area</th>
      <th @click="sort('commission')">Commission</th>
      <th @click="sort('phoneNo')">Phone Number</th>
      <th @click="sort('country')">Agent Country</th>
      <th>Edit</th>
    </tr>
    </thead>
    <tbody v-for="agent in sortedAgents" :key="agent.agentCode">
  <tr class="table">
    <td>{{ agent.agentCode }}</td>
    <td>{{ agent.agentName}}</td>
    <td>{{ agent.workingArea}}</td>
    <td>{{ agent.commission}}</td>
    <td>{{ agent.phoneNo }}</td>
    <td>{{ agent.country }}</td>
    <td><button @click="editAgent(agent.agentCode)">Edit</button></td>
  </tr>
    </tbody>
  </table>
  <router-view></router-view>
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
      currentSortDir:'asc',
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
      let data = agentCode

      this.$router.push({
        name: "updateAgentPage",
        params:  {data}
      });
      console.log(data)

    }
  }
}
</script>

<style scoped>

button {
  background-color: transparent;
  color: hsla(160, 100%, 37%, 1);
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  transition: 0.4s;
}
table,
th {
  border-collapse: collapse;
  padding: 0.5em 1em;
  border: 2px solid black;
  color:hsla(160, 100%, 100%, 1);
  background-color: hsla(160, 100%, 100%, 0.1);
  text-align: center;
}
.thead th{
  background-color: hsla(160, 100%, 37%, 1);
  color: hsla(160, 100%, 100%, 1);
  position: sticky;
  top: 0;
  text-align: center;
  border: 2px solid black;
}

@media (hover: hover) {
  a, button:hover {
    background-color: hsla(166, 100%, 37%, 0.2);
  }
}
</style>