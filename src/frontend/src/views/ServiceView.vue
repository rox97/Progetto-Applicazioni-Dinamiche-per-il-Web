<template>
  <main>
    <header>
    <h1>
    Ruolo: {{role}}
    </h1>
    <div class="wrapper">
      <nav role="navigation" aria-label="navigation">
        <RouterLink to="/test">Ordini</RouterLink>
        <RouterLink to="/test" v-if="role === 'admin'">Agenti</RouterLink>
        <RouterLink to="/test" v-if="role === 'admin'">Clienti</RouterLink>
        <RouterLink to="/about">About</RouterLink>
        <RouterLink to="/test">Test</RouterLink>
        <RouterLink to="/login" @click="logout">Logout</RouterLink>
      </nav>
    </div>
    </header>
  </main>

</template>

<script>
export default {
  name: "ServiceView",
  data() {
    return {
      role : localStorage.getItem('userLogged')
    };
  },
  methods:{
    logout(){
      localStorage.setItem('userLogged' , 'false');
    }
    }
}

</script>

<style scoped>
@import "@/assets/base.css";

@media (hover: hover) {
  a:hover {
    background-color: hsla(166, 100%, 37%, 0.2);
  }
}

h1 {
  text-align: center;
  margin-bottom: 2rem;
  font-size: 80px;

}

nav {
  width: 100%;
  font-size: 40px;
  text-align: center;
  margin-top: 2rem;
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}


nav a:first-of-type {
  border: 0;
}

@media (min-width: 1024px) {

  nav {
    text-align: center;
    font-size: 3rem;

    padding: 1rem 0;
    margin-top: 2rem;
  }

}

</style>