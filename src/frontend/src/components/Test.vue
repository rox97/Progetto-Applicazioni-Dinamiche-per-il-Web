<template>
  <div class="test">
    <h1>{{ msg }}</h1>
  </div>
</template>

<script>
export default {
  name: 'TestPage',
  data() {
    return {
      msg: 'Il web server non è acceso'
    }
  },
  mounted() {
    fetch("/api/test")
        .then((response) => response.text())
        .then((data) => {
          this.msg = data;
        });
  }
}
</script>


