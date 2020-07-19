<template>
  <div>
    <h2 class="page-h2">login Page</h2>
    <h4>닉네임을 입력해주세요</h4>
    <div>
      <form @submit.prevent="submitForm" class="form">
        <input type="text" v-model="userName" />
        <button>로그인</button>
      </form>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      userName: '',
    };
  },
  methods: {
    async submitForm() {
      this.$store.state.userName = this.userName;
      try {
        const userData = {
          userId: this.userName,
        };
        axios
          .post('http://3.34.15.148/api/login', userData)
          .then(data => console.log(data));
        this.$router.push('/select');
      } catch (error) {
        // 에러 핸들링할 코드
        console.log(error);
      } finally {
        this.initForm();
      }
    },
    initForm() {
      this.userName = '';
    },
  },
};
</script>

<style scoped>
.page-h2 {
  color: red;
}
</style>
