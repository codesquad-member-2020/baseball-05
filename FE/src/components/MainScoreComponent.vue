<template>
  <div class="container">
    <h2>BASEBALL GAME ONLINE</h2>
    <div class="match-info-container">
      <span class="home-team-title">
        {{ matchArr.homeTeam.name }}
      </span>
      <span class="home-team-score">
        {{ matchArr.homeTeam.score }}
      </span>
      vs
      <span class="away-team-score">
        {{ matchArr.awayTeam.score }}
      </span>
      <span class="away-team-title">
        {{ matchArr.awayTeam.name }}
      </span>
    </div>
    <div v-if="matchArr.homeTeam.isUserTeam">
      A가 우리팀
    </div>
    <div v-else>
      B가 우리팀
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';

export default {
  data() {
    return {};
  },
  computed: {
    ...mapState(['matchArr']),
  },
};
</script>

<style></style>
