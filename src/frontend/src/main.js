import Vue, { createApp } from "vue";
import { createPinia } from "pinia";

import App from "./App.vue";
import router from "./router";

import VueApollo from "vue-apollo";
import apolloClient from "./apollo";

const app = createApp(App);

const apolloProvider = new VueApollo({
  defaultClient: apolloClient,
});

new Vue({
  render: (h) => h(App),
  router,
  provide: apolloProvider.provide(),
});

//app.use(createPinia());
app.use(router);

app.mount("#app");
