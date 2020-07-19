<template>
  <div class="exit-btn-container">
    <md-button @click="setGameExit" class="md-raised md-accent"
      >나가기</md-button
    >
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'RegularButtons',
  methods: {
    setGameExit() {
      axios
        .put(
          `http://3.34.15.148/api/admin/resetMatch?matchId=${this.$route.query.matchId}`,
        )
        .then(this.$router.push('/'));
    },
  },
};
</script>

<style scoped>
.exit-btn-container {
  position: absolute;
  bottom: 5%;
  right: 15%;
}

.exit-btn {
}
</style>
