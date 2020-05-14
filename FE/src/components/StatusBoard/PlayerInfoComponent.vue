<template>
  <div>
    <div
      class="player-toggle-container"
      @mouseover="toggleContainer"
      @mouseleave="toggleContainer"
    ></div>
    <div
      class="player-container"
      :class="{ fadeIn: this.toggleValue, fadeOut: !this.toggleValue }"
    >
      <table v-for="team in this.playerInfo" :key="team.team">
        <thead>
          <th data-th="Team" colspan="5">{{ team.team }}</th>
        </thead>
        <tbody>
          <tr>
            <td>타자</td>
            <td>타석</td>
            <td>안타</td>
            <td>아웃</td>
            <td>평균</td>
          </tr>
          <tr v-for="player in team.roundRecordDtos" :key="player.index">
            <td>{{ player.player }}</td>
            <td>{{ player.mounts }}</td>
            <td>{{ player.hits }}</td>
            <td>{{ player.outs }}</td>
            <td>{{ player.average }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import { fetchRoters } from '@/api/game';

export default {
  data() {
    return {
      playerInfo: [],
      toggleValue: false,
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      const { data } = await fetchRoters();
      this.playerInfo = data;
    },

    toggleContainer() {
      this.toggleValue = !this.toggleValue;
    },
  },
};
</script>

<style scoped>
.player-container {
  position: absolute;
  display: flex;
  right: 15%;
  bottom: -95%;
  background-color: #00000099;
  margin: 0 auto;
  width: 800px;
  padding: 20px;
  animation-duration: 1s;
  animation-fill-mode: both;
}

.player-toggle-container {
  position: absolute;
  width: 100%;
  background: #ffffff00;
  height: 25%;
  z-index: 1;
  bottom: -140px;
}

/* .player-toggle-container:hover {
  background: #fff;
} */

table {
  border-collapse: collapse;
  font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
  margin: 0 auto;
  border: 1px solid #fff;
}

table,
th,
tr,
td {
  padding: 0.5em 0.75em;
  color: #fff;
  text-align: center;
  border-top: 1px solid #fff;
  border-bottom: 1px solid #fff;
}

[data-th='Team'] {
  font-weight: bold;
}

.fadeIn {
  animation-name: fadeIn;
}

.fadeOut {
  animation-name: fadeOut;
}

@keyframes fadeIn {
  0% {
    bottom: -95%;
  }
  100% {
    bottom: -10%;
  }
}

@keyframes fadeOut {
  0% {
    bottom: -10%;
  }
  100% {
    bottom: -95%;
  }
}
</style>
