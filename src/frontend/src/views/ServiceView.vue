<template>
  <title>Service</title>
  <main>
    <header>
    <h1>
    {{role}}: {{code}}
    </h1>
    <div>
      <nav role="navigation" aria-label="navigation">
        <RouterLink id="orders" to="/orders">Orders</RouterLink>
        <RouterLink id="agents" to="/agents" v-if="role === 'admin'">Agents</RouterLink>
        <RouterLink id="customers" to="/customers" v-if="role === 'admin'">Customers</RouterLink>
        <RouterLink id="logout" to="/login" @click="logout">Logout</RouterLink>
      </nav>
    </div>
    </header>
    <RouterView />
  </main>

</template>

<script>
export default {
  name: "ServiceView",
  data() {
    return {
      role : localStorage.getItem('userRole'),
      code : localStorage.getItem('userCode')
    };
  },
  methods: {
    logout() {
      localStorage.removeItem('userLogged');
      localStorage.removeItem('userRole');
      localStorage.removeItem('userCode');
      //this.$router.push({path: '/login'});
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
  font-size: 50px;

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
h1::first-letter {
  text-transform: capitalize;
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