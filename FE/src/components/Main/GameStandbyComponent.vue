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
import axios from 'axios';

export default {
  data() {
    return {
      isUserSearch: false,
    };
  },
  created() {
    this.$confetti.stop();
    this.syncData();
  },
  components: {
    LoadingSpinner,
  },

  methods: {
    async fetchData() {
      const { data } = await this.fetchSearchUser();
      console.log(data);
      this.isUserSearch = data.status;
    },

    async fetchSearchUser() {
      return axios.get(
        `http://3.34.15.148/api/start?matchId=${this.$store.state.matchId}`,
      );
    },

    syncData() {
      if (this.isUserSearch)
        return this.$router.push({
          path: `/game/${this.$route.params.id}`,
          query: {
            matchId: this.$route.query.matchId,
          },
        });

      setTimeout(() => {
        this.fetchData();
        this.syncData();
      }, 5000);
    },
  },
};
</script>

<style scoped>
.loading-container {
  width: 100%;
  height: 100%;
  background: #000;
  color: red;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
  margin: 0 auto;
  /* width: 600px; */
}
</style>
