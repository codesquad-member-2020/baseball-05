<template>
  <div class="ballImg" />
</template>

<script>
export default {};
</script>

<style scoped>
.ballImg {
  position: absolute;
  height: 10px;
  width: 10px;
  border-radius: 50%;
  background-color: #fff;
  right: 49%;
  top: 66%;
  z-index: 1;
  animation-duration: 1.3s;
  animation-fill-mode: both;
}

/* .end {
  animation-name: end;
} */
/*
@keyframes end {
  0% {
  }
  100% {
  }
} */
</style>
