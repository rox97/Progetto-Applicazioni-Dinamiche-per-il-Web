import {createApp, h, provide} from "vue";

import App from "./App.vue";
import router from "./router";
import {
  ApolloClient,
  createHttpLink,
  InMemoryCache,
} from "@apollo/client/core";
import {DefaultApolloClient} from "@vue/apollo-composable";
import { createApolloProvider } from "@vue/apollo-option";


const httpLink = createHttpLink({
  uri: "http://localhost:8080/graphql",

});

const apolloClient = new ApolloClient({
  link: httpLink,
  cache: new InMemoryCache(),
});

export const provider = createApolloProvider({defaultClient: apolloClient});

const app = createApp({
  setup() {
    provide(DefaultApolloClient, apolloClient);
  },
  apollo:{},
  render:() => h(App),
});
app.use(provider);

app.use(router);

app.mount("#app");


