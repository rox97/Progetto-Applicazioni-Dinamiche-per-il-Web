import { HttpLink } from "apollo-link-http";
import { ApolloClient } from "apollo-client";
import { InMemoryCache } from "apollo-cache-inmemory";
import Vue from "vue";
import VueApollo from "vue-apollo";


const httpLink = new HttpLink({
  uri: "http://localhost:8080/graphql",
});

const apolloClient = new ApolloClient({
  link: httpLink,
  cache: new InMemoryCache(),
  connectToDevTools: true,
});
Vue.use(VueApollo);


export default apolloClient;

export const apolloProvider = new VueApollo({
  defaultClient: apolloClient
})
