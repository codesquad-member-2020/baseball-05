<template>
  <div class="login-container">
    <login-component></login-component>
  </div>
</template>

<script>
import LoginComponent from '@/components/Login/LoginComponent';

export default {
  components: {
    LoginComponent,
  },
};
</script>

<style>
.login-container {
  /* border: 1px solid #ccc; */
  background-color: #00000099;
  border-radius: 3px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
  margin: 0 auto;
  width: 600px;
  height: 200px;
}
</style>
