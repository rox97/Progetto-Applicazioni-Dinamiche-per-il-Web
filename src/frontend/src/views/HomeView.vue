<script setup>
import TheWelcome from "@/components/TheWelcome.vue";
import Login from "@/components/Login.vue";
localStorage.setItem('userLogged', "false");
</script>

<template>
  <main>
    <!--<TheWelcome />-->
    <Login />
  </main>
</template>
