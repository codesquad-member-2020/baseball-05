<template>
  <div class="component-container">
    <main-score-component></main-score-component>
    <player-component></player-component>
    <game-board></game-board>
    <game-log-component></game-log-component>
  </div>
</template>

<script>
import MainScoreComponent from '@/components/MainScoreComponent';
import PlayerComponent from '@/components/PlayerComponent';
import GameBoard from '@/components/GameBoardComponent';
import GameLogComponent from '@/components/GameLogComponent';

export default {
  components: {
    MainScoreComponent,
    PlayerComponent,
    GameBoard,
    GameLogComponent,
  },
};
</script>

<style>
.component-container {
  display: flex;
}
</style>
