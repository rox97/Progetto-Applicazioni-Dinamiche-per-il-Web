<template>
  <div class="test">
    <h1>Test </h1>
    <tr v-for="agent in agents">
      <td>{{ agent.agentCode }}</td>
      <td>{{ agent.agentName }}</td>

    </tr>
  </div>
</template>

<script>
export default {
  name: 'TestPage',
  data() {
    return {
      agents: null
    }
  },
  mounted() {
    fetch("/api/test", {method: "GET"})
        .then((response) => response.json())
        .then((agents) => {
          this.agents = agents;
        });
  }
}
</script>


