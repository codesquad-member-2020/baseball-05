<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {};
</script>

<style>
body {
  /* background-image: url('./assets/bg.jpg');
  background-color: #41b883;
  background-repeat: no-repeat;
  background-size: cover;
  color: #fff; */
}
body {
  margin: 0;
}
#app {
  background: url('https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/Angels_Stadium.JPG/1200px-Angels_Stadium.JPG');
  /* center no-repeat; */
  background-size: cover;
  background-repeat: no-repeat;
  min-height: 100vh;
}
li {
  list-style: none;
}
</style>
