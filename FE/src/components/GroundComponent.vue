<template>
  <div class="ground">
    <ball :class="{ start: throwBall, fly: flyBall }" />
    <player class="default" :class="{ active: this.$store.state.waitBatter }" />
    <div class="ground-wrap" v-for="player in basePlayers" :key="player.index">
      <player :class="player" />
    </div>
  </div>
</template>

<script>
import Player from '@/components/Player';
import Ball from '@/components/Ball';
import { mapState } from 'vuex';

export default {
  data() {
    return {};
  },

  computed: {
    ...mapState(['basePlayers', 'currentBatter', 'throwBall', 'flyBall']),
  },

  components: {
    Player,
    Ball,
  },
};
</script>

<style scoped>
.ground {
  background-color: #000;
}

.active {
  display: none;
}

.start {
  animation-name: start;
}

.fly {
  animation-name: fly;
}

@keyframes fly {
  0% {
    top: 91%;
  }
  100% {
    right: 37%;
    top: 32%;
  }
}

@keyframes start {
  0% {
    top: 66%;
  }
  100% {
    top: 91%;
  }
}
</style>
