<template>
  <div class="ground-container">
    <ground-component></ground-component>
    <game-status-component
      :propsData="initGameRenderData"
    ></game-status-component>
    <play-button :class="{ btntoggle: !buttonToggle }"></play-button>
    <game-log-component></game-log-component>
    <score-info-component></score-info-component>
    <player-info-component></player-info-component>
    <score-component :propsData="initGameRenderData"></score-component>
    <game-exit-component></game-exit-component>
  </div>
</template>

<script>
import GameExitComponent from '@/components/GameExitComponent';
import GroundComponent from '@/components/GroundComponent';
import PlayButton from '@/components/PlayButton';
import GameLogComponent from '@/components/Game/GameLogComponent';
import ScoreInfoComponent from '@/components/StatusBoard/ScoreInfoComponent';
import PlayerInfoComponent from '@/components/StatusBoard/PlayerInfoComponent';
import GameStatusComponent from '@/components/GameStatusComponent';
import ScoreComponent from '@/components/ScoreComponent';
import { fetchGames } from '@/api/game';

export default {
  data() {
    return {
      initGameRenderData: [],
      buttonToggle: false,
    };
  },
  async created() {
    await this.fetchData();
  },
  components: {
    GroundComponent,
    PlayButton,
    GameLogComponent,
    ScoreInfoComponent,
    PlayerInfoComponent,
    GameStatusComponent,
    ScoreComponent,
    GameExitComponent,
  },
  methods: {
    async fetchData() {
      const { data } = await fetchGames(this.$store.state.matchId);
      console.log(data);
      this.initGameRenderData = data;
      this.initRender();
    },

    initRender() {
      console.log(this.$route.params.id); // 홈인지 어웨이인지 판단
      console.log(this.initGameRenderData.isFirstHalf); // 초인지 말인지 판단
      if (
        this.initGameRenderData.isFirstHalf &&
        this.$route.params.id === 'home'
      ) {
        return (this.buttonToggle = true);
      }
      if (
        this.initGameRenderData.isFirstHalf &&
        this.$route.params.id === 'away'
      ) {
        setTimeout(() => {
          this.fetchData();
        }, 3000);
        return (this.buttonToggle = false);
      }
      if (
        !this.initGameRenderData.isFirstHalf &&
        this.$route.params.id === 'home'
      ) {
        setTimeout(() => {
          this.fetchData();
        }, 3000);
        return (this.buttonToggle = false);
      }
      if (
        !this.initGameRenderData.isFirstHalf &&
        this.$route.params.id === 'away'
      ) {
        return (this.buttonToggle = true);
      }
    },
  },
};
</script>

<style scoped>
/* #app {
  background: #000;
  z-index: 1;
  background-size: cover;
  background-repeat: no-repeat;
  min-height: 100vh;
} */

.ground-container {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 1200px;
  height: 700px;
  background: url('https://s3-us-west-2.amazonaws.com/mfbrowndesign.com/img/baseball+diamond.jpg')
    no-repeat;
  background-size: 100% 100%;
}

.btntoggle {
  display: none;
}
</style>
