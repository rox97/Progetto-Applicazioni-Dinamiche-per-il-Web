<script setup>
import {RouterLink, RouterView} from 'vue-router';
</script>

<template>
  <RouterView />
</template>

<script>

  export default {
    name: "App",
  }

</script>

<style>
@import "@/assets/base.css";

#app {
  max-width: 1280px;
  margin: auto;
  padding: 2rem 2rem 2rem 2rem;
  font-weight: normal;
}

h1,h2,h3,h4,h5,h6 {
  margin:0;
  font-weight: normal;
}

a{
  text-decoration: none;
  color: hsla(160, 100%, 37%, 1);
  transition: 0.4s;
}

@media (hover: hover) {
  a:hover {
    background-color: hsla(166, 100%, 37%, 0.2);
  }
}

nav {
  width: 100%;
  font-size: 12px;
  text-align: center;
  margin-top: 2rem;
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}

@media (min-width: 1024px) {
  body {
    align-content: center;
  }

  #app {
    display: grid;
    grid-template-columns: 1fr;
    padding: 0 2rem;
  }

  nav {
    text-align: left;
    margin-left: -1rem;
    font-size: 1rem;

    padding: 1rem 0;
    margin-top: 1rem;
  }
}
@media (prefers-color-scheme: light) {
  a{
    color: #008558
  }
}
</style>
