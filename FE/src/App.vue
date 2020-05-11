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
  margin: 0;
  overflow: hidden;
}
#app {
  background: url('https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/Angels_Stadium.JPG/1200px-Angels_Stadium.JPG');
  background-size: cover;
  background-repeat: no-repeat;
  min-height: 100vh;
}
li {
  list-style: none;
}
</style>
