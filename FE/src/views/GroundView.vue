<template>
  <div class="ground-container">
    <ground-component></ground-component>
    <play-button></play-button>
    <game-log-component></game-log-component>
    <score-info-component></score-info-component>
    <player-info-component></player-info-component>
    <player-info-button></player-info-button>
    <score-info-button></score-info-button>
  </div>
</template>

<script>
import GroundComponent from '@/components/GroundComponent';
import PlayButton from '@/components/PlayButton';
import GameLogComponent from '@/components/Game/GameLogComponent';
import ScoreInfoComponent from '@/components/StatusBoard/ScoreInfoComponent';
import PlayerInfoComponent from '@/components/StatusBoard/PlayerInfoComponent';
import PlayerInfoButton from '@/components/PlayerInfoButton';
import ScoreInfoButton from '@/components/ScoreInfoButton';

export default {
  components: {
    GroundComponent,
    PlayButton,
    GameLogComponent,
    ScoreInfoComponent,
    PlayerInfoComponent,
    PlayerInfoButton,
    ScoreInfoButton,
  },
};
</script>

<style>
#app {
  background: #000;
}

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
</style>
