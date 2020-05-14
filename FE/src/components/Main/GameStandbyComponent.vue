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
      console.log(this.isUserSearch);
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
