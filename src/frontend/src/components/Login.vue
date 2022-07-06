<template>
  <title>Login Page</title>
  <div class="login" >
  <h1>Login Page</h1>
    <div class="imgcontainer" aria-label="avatar image">
      <img src="https://www.my-personaltrainer.it/2021/04/13/alimentazione-gatto-orig.jpeg" alt="avatar" class="avatar">
    </div>
  </div>
  <div class="container">
    <div class="form-group">
      <label for="username" aria-label="username" ><b>Username</b></label>
      <input v-on:keyup.enter="auth" id="username" type="text" ref="username" placeholder="Enter Username" name="username"><br>
    </div>
    <div class="form-group">
    <label for="password" aria-label="password"><b>Password</b></label>
    <input v-on:keyup.enter="auth" id="password" type="password" ref="password" placeholder="Enter Password" name="password"><br>
    </div>
    <button type="submit" @click="auth">Login</button>
    <div v-if="msg">{{msg}}</div>
  </div>
</template>

<script>
import {gql} from 'graphql-tag'
export default {

  name: "LoginPage",
  data() {

    return {
      msg: ''
    }
  },
  methods: {
    auth() {
      let username = this.$refs.username.value;
      let password = this.$refs.password.value;

      this.$apollo.query({
        query: gql`
          query {
            userByUserAndPswd(user_code: "${username}", user_password: "${password}") {
            user_code,
            user_role
            }
          }
        `
      }).then(({data}) => {
        if (data.userByUserAndPswd) {
          localStorage.setItem("userRole", data.userByUserAndPswd.user_role);
          localStorage.setItem("userCode", data.userByUserAndPswd.user_code);
          localStorage.setItem("userLogged", "true");
          this.$router.push({path: 'api/service'});
        } else {
          this.msg = 'Wrong username or password';
        }
      });
    }
  }
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

button {
  background-color: #00BD7EFF;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  font-size: 2rem;
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

@media screen and (max-width: 1024px) {
}
@media (prefers-color-scheme: light){
  button{
    background-color: #008558
  }
  h1{
    color: black;
  }
}
</style>