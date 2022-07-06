<script setup>
import Login from "@/components/Login.vue";
localStorage.setItem('userLogged', "false");
</script>

<template>
  <main>
    <Login />
  </main>
</template>
