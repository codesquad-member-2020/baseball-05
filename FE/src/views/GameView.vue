<template>
  <div>
    <main-score-component></main-score-component>
    <player-component></player-component>
  </div>
</template>

<script>
import MainScoreComponent from '@/components/MainScoreComponent';
import PlayerComponent from '@/components/PlayerComponent';

export default {
  components: {
    MainScoreComponent,
    PlayerComponent,
  },
};
</script>

<style></style>
