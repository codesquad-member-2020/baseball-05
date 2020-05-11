<template>
  <div class="ground">
    <player class="default" :class="{ active: this.$store.state.waitBatter }" />
    <div class="ground-wrap" v-for="player in basePlayers" :key="player.index">
      <player :class="player" />
    </div>
  </div>
</template>

<script>
import Player from '@/components/Player';
import { mapState } from 'vuex';

export default {
  data() {
    return {};
  },

  computed: {
    ...mapState(['basePlayers', 'currentBatter']),
  },

  components: {
    Player,
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
</style>
