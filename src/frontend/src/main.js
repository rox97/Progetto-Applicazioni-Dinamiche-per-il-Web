import { createApp } from "vue";

import App from "./App.vue";
import router from "./router";
import {
  ApolloClient,
  createHttpLink,
  InMemoryCache,
} from "@apollo/client/core";
import { DefaultApolloClient } from "@vue/apollo-composable";

const app = createApp(App);

const httpLink = createHttpLink({
  uri: "http://localhost:8080/graphql",

});
const cache = new InMemoryCache();
const apolloClient = new ApolloClient({
  link: httpLink,
  cache,
});
export default apolloClient;
app.provide(DefaultApolloClient, apolloClient);
app.use(router);

app.mount("#app");
