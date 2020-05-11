<template>
  <div>
    <div v-if="!matchArr.homeTeam.isOffense">
      <div class="pitcher-container">
        <div>투수</div>
        <span>{{ `${matchArr.homeTeam.pitcher.name}` }}</span>
        <span>{{ ` #투구 수 ${matchArr.homeTeam.pitcher.round}` }}</span>
      </div>
      <div class="batter-container">
        <div>타자</div>
        <div>{{ `${matchArr.homeTeam.batter.name}` }}</div>
        <span>{{ `${matchArr.homeTeam.batter.mount}타석` }}</span>
        <span>{{ `${matchArr.homeTeam.batter.hit}안타` }}</span>
      </div>
    </div>
    <div v-else>
      <div class="pitcher-container">
        <div>투수</div>
        <span>{{ `${matchArr.awayTeam.pitcher.name}` }}</span>
        <span>{{ ` #투구 수 ${matchArr.awayTeam.pitcher.round}` }}</span>
      </div>
      <div class="batter-container">
        <div>타자</div>
        <div>{{ `${matchArr.awayTeam.batter.name}` }}</div>
        <span>{{ `${matchArr.awayTeam.batter.mount}타석` }}</span>
        <span>{{ `${matchArr.awayTeam.batter.hit}안타` }}</span>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';

export default {
  computed: {
    ...mapState(['matchArr']),
  },
};
</script>

<style scoped>
.pitcher-container {
}

.batter-container {
}
</style>
