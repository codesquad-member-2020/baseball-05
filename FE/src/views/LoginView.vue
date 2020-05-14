<template>
  <div>
    login Page
    <score-info-component></score-info-component>
    <player-info-component></player-info-component>
    <loading-spinner></loading-spinner>
  </div>
</template>

<script>
import ScoreInfoComponent from '@/components/StatusBoard/ScoreInfoComponent';
import PlayerInfoComponent from '@/components/StatusBoard/PlayerInfoComponent';
import LoadingSpinner from '@/components/common/LoadingSpinner';

export default {
  components: {
    LoadingSpinner,
    ScoreInfoComponent,
    PlayerInfoComponent,
  },
};
</script>

<style></style>
