<template>
  <div>
    <div class="batter-btn">
      <button @click="toggleLogArea">타자 기록</button>
    </div>
    <div class="log-container" :class="{ fadeIn: toggleValue }">
      <div v-for="info in plate" :key="info.id">
        <div v-if="info.id === 1" class="current-batter">
          <span>{{ info.plate }}</span>
          <span>번 타자 </span>
          <span>{{ info.batter.name }}</span>
        </div>
        <div v-else class="log-batter">
          <span>{{ info.plate }}</span>
          <span>번 타자 </span>
          <span>{{ info.batter.name }}</span>
        </div>
        <ul>
          <li v-for="log in info.rounds.slice().reverse()" :key="log.data">
            <span>{{ log.decision }}</span>
            <span v-if="log.decision == '안타!'"> </span>
            <span v-else>
              <span>S{{ log.strike }}</span>
              <span>B{{ log.ball }}</span>
            </span>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';

export default {
  data() {
    return {
      toggleValue: false,
    };
  },
  computed: {
    ...mapState(['plate']),
  },
  methods: {
    toggleLogArea() {
      this.toggleValue = !this.toggleValue;
    },
  },
};
</script>

<style scoped>
.log-container {
  color: #fff;
  position: absolute;
  right: -40%;
  height: 500px;
  top: 15%;
  width: 210px;
  background: #000000;
  outline: 1px solid #fff;
  overflow: auto;
  padding: 20px;
  animation-duration: 1s;
  animation-fill-mode: both;
}

.batter-btn {
  position: absolute;
  top: 16%;
  right: 37%;
  background-color: #fff;
}

.log-container::-webkit-scrollbar {
  display: none;
}

.current-batter {
  color: red;
}
.log-batter {
  color: rgb(0, 68, 255);
}

.fadeIn {
  animation-name: fadeIn;
}

.fadeOut {
  animation-name: fadeOut;
}

@keyframes fadeIn {
  0% {
    right: -40%;
  }
  100% {
    right: -10%;
  }
}

@keyframes fadeOut {
  0% {
    right: -40%;
  }
  100% {
    right: -10%;
  }
}
</style>
