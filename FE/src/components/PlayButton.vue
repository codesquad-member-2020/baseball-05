<template>
  <div>
    <button class="playBtn" @click="throwBall"></button>
  </div>
</template>

<script>
import { mapState } from 'vuex';

export default {
  computed: {
    ...mapState(['currentBatter', 'basePlayers']),
  },
  methods: {
    throwBall() {
      console.log('throw');
    },
  },
};
</script>

<style>
.playBtn {
  position: absolute;
  bottom: 220px;
  left: 50%;
  transform: translateX(-50%);
  width: 50px;
  height: 60px;
  background: url('../assets/pitcher.png') no-repeat;
  background-size: 100% 100%;
}
</style>
