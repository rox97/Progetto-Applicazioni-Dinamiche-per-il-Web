import { createApp } from "vue";

import App from "./App.vue";
import router from "./router";
import {
  ApolloClient,
  createHttpLink,
  InMemoryCache,
} from "@apollo/client/core";
import {DefaultApolloClient} from "@vue/apollo-composable";
import VueApollo from "vue-apollo";

const app = createApp(App);

const httpLink = createHttpLink({
  uri: "http://localhost:8080/graphql",

});
const apolloClient = new ApolloClient({
  link: httpLink,
  cache: new InMemoryCache(),
  connectToDevTools: true,
});
app.component("Login", () => import("./components/Login.vue"));
app.provide(DefaultApolloClient, apolloClient);
app.use(router);
//app.use(VueApollo);

app.mount("#app");

export const apolloProvider = new VueApollo({
  defaultClient: apolloClient
})
