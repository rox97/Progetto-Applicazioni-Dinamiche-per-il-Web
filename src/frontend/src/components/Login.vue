<script setup>
import { useQuery } from '@vue/apollo-composable'
import {gql} from 'graphql-tag'
const { result } = useQuery(gql`
  query {
    allAgents {
      agentCode
      agentName
      workingArea
      commission
    }
  }
`)
</script>

<template>
  <head>
    <title>WebApp</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </head>

  <div class="login">
  <h1>Login</h1>
    <div class="imgcontainer">
      <img src="https://www.my-personaltrainer.it/2021/04/13/alimentazione-gatto-orig.jpeg" alt="avatar" class="avatar">
    </div>
  </div>
  <div class="container">
  <form action="mainPage" method="post">
    <label for="userName"><b>Username</b></label>
    <input id="userName" type="text" placeholder="Enter Username" name="userName"><br>
    <label for="password"><b>Password</b></label>
    <input id="password" type="password" placeholder="Enter Password" name="password"><br>
    <input type="submit" value="Login" />
    <button type="submit">Login</button>

    <div >Messaggio: {{msg}}</div>


    <div >Risultato query: {{result}}</div>

    <div class="query">Risultato seconda query: {{agents}}</div>


  </form>
  </div>
</template>

<script>
import { useQuery } from '@vue/apollo-composable'
import {gql} from 'graphql-tag'
let agents = ''

export default {

  name: "LoginPage",
  data() {

    return {
      msg: 'Il web server non è acceso',
      agents: ''
    }
  },
  query(){
    return agents = useQuery(gql`query {
      allAgents {
        agentCode
      }
    }`)
  },

}

</script>

<style scoped>

h1 {
  text-align: center;
  color: white;
}

/* Full-width inputs */
input[type=text], input[type=password] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

/* Set a style for all buttons */

input[type=submit] {
  background-color: #00BD7EFF;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}

button {
  background-color: #00BD7EFF;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}

/* Add a hover effect for buttons */
button:hover {
  opacity: 0.8;
}

/* Center the avatar image inside this container */
.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
}

/* Avatar image */
img.avatar {
  width: 40%;
  border-radius: 50%;
}

/* Add padding to containers */
.container {
  padding: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 1024px) {

}
</style>