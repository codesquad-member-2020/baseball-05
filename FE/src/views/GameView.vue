<template>
  <div class="component-container">
    <div class="top-container">
      <div class="score-container">
        <main-score-component></main-score-component>
      </div>
      <div class="player-container">
        <player-component></player-component>
      </div>
    </div>
    <div class="bottom-container">
      <div class="game-container">
        <game-board></game-board>
      </div>
      <div class="log-container">
        <game-log-component></game-log-component>
      </div>
    </div>
  </div>
</template>

<script>
import MainScoreComponent from '@/components/Game/MainScoreComponent';
import PlayerComponent from '@/components/Game/PlayerComponent';
import GameBoard from '@/components/Game/GameBoardComponent';
import GameLogComponent from '@/components/Game/GameLogComponent';
import { fetchGames } from '@/api/game';

export default {
  data() {
    return {
      gameInfo: [],
    };
  },
  components: {
    MainScoreComponent,
    PlayerComponent,
    GameBoard,
    GameLogComponent,
  },
  created() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      // const { data } = await fetchGames();
      // console.log(data);
      // vuex에 저장
    },
  },
};
</script>

<style scoped>
.component-container {
  margin: 0 auto;
  background-color: #ccc;
  width: 80vw;
  transform: translate(-50%, -50%);
  position: absolute;
  top: 50%;
  left: 50%;
}
.top-container {
  border-bottom: 1px solid white;
  display: flex;
  height: 30vh;
  width: 80vw;
}
.score-container {
  background-color: #ccc;
  border-right: 1px solid white;
  height: 100%;
  width: 60vw;
}
.player-container {
  background-color: skyblue;
  height: 100%;
  width: 20vw;
}
.bottom-container {
  display: flex;
  height: 50vh;
  width: 80vw;
}
.game-container {
  border-right: 1px solid white;
  height: 100%;
  width: 60vw;
  display: flex;
  align-items: center;
}
.log-container {
  background-color: #fff;
  height: 100%;
  width: 20vw;
  overflow: auto;
}

.log-container::-webkit-scrollbar {
  display: none;
}
</style>
