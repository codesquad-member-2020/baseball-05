<template>
  <div>
    <button
      class="playBtn"
      :class="{ active: this.$store.state.battingDelay }"
      @click="throwBall"
    ></button>
  </div>
</template>

<script>
import hit from '../assets/hit.mp3';
import { mapState } from 'vuex';

export default {
  data() {
    return {
      baseStatus: [],
    };
  },
  computed: {
    ...mapState(['currentBatter', 'basePlayers', 'battingDelay']),
    // 나중에 mutations 에 연결해서 써야할듯
  },
  methods: {
    sound() {
      var play = new Audio(hit);
      play.play();
    },
    throwBall() {
      this.$store.state.throwBall = !this.$store.state.throwBall;
      setTimeout(() => {
        this.$store.state.flyBall = !this.$store.state.flyBall;
      }, 1000);
      this.$store.state.battingDelay = true;
      this.sound();
      setTimeout(() => {
        this.$store.state.waitBatter = true;
        this.$store.state.throwBall = !this.$store.state.throwBall;
        setTimeout(() => {
          this.$store.state.battingDelay = false;
          this.$store.state.waitBatter = false;
          this.$store.state.flyBall = !this.$store.state.flyBall;
        }, 2000);
        this.setBaseRun();
      }, 2000);
    },

    setBaseRun() {
      const currentBase = [];
      this.$store.state.basePlayers.map((base, index) => {
        if (index > 3) return;
        switch (base) {
          case 'first':
            currentBase.push('second');
            break;
          case 'second':
            currentBase.push('third');
            break;
          case 'third':
            currentBase.push('home');
            break;
          case 'home':
            currentBase.push('first');
            break;
        }
      });

      if (currentBase.length < 4) {
        currentBase.push('first');
      }
      this.$store.state.basePlayers = currentBase;
    },
  },
};
</script>

<style scoped>
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

.active {
  pointer-events: none;
  display: block;
}
</style>
