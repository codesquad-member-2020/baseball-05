<template>
  <div class="ground">
    <player :class="{ active: currentBatter }" />
    <div class="ground-wrap" v-for="player in players" :key="player.index">
      <player :class="player" />
    </div>
  </div>
</template>

<script>
import Player from '@/components/Player';

export default {
  data() {
    return {
      players: ['first', 'second', 'third'],
      currentBatter: true,
    };
  },
  components: {
    Player,
  },
};
</script>

<style>
.ground {
  background-color: #000;
}

.active {
  display: none;
}
</style>
