<template>
  <div>
    <div class="loading-container">
      <loading-spinner></loading-spinner>
      <span>상대방이 들어오기를 기다리는 중입니다...</span>
    </div>
  </div>
</template>

<script>
import LoadingSpinner from '@/components/common/LoadingSpinner';

export default {
  created() {
    // /api/start를 setTimeout으로 계속 요청 보내야 함
  },
  components: {
    LoadingSpinner,
  },
};
</script>

<style>
#app {
  background: #000;
}

.loading-container {
  color: red;
  /* border: 1px solid #ccc; */
  /* background-color: #00000080; */
  border-radius: 3px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
  margin: 0 auto;
  /* width: 600px; */
}
</style>
