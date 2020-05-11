<template>
  <div>
    login Page
    <score-info-component></score-info-component>
    <player-info-component></player-info-component>
  </div>
</template>

<script>
import ScoreInfoComponent from '@/components/StatusBoard/ScoreInfoComponent';
import PlayerInfoComponent from '@/components/StatusBoard/PlayerInfoComponent';
export default {
  components: {
    ScoreInfoComponent,
    PlayerInfoComponent,
  },
};
</script>

<style></style>
