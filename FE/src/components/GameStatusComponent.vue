<template>
  <div class="flex-container" v-if="!propsData.homeTeam">
    loading...
  </div>
  <div v-else class="flex-container">
    <div class="flex-item">
      <div class="home-container">
        <div class="isOffense" v-if="propsData.homeTeam.isOffense">공격</div>
        <div class="home-team-name team-name">
          {{ propsData.homeTeam.name }}
        </div>
        <div class="home-team-score score">{{ propsData.homeTeam.score }}</div>
      </div>
      <div>{{ propsData.inning.id }}회 {{ propsData.inning.half }}</div>
      <div class="inning-info">
        <div v-if="basePlayers.length === 0">
          <div class="base-info first-base"></div>
          <div class="base-info second-base"></div>
          <div class="base-info third-base"></div>
        </div>
        <div v-if="basePlayers.length === 1">
          <div class="base-info first-base fill-base"></div>
          <div class="base-info second-base"></div>
          <div class="base-info third-base"></div>
        </div>
        <div v-if="basePlayers.length === 2">
          <div class="base-info first-base fill-base"></div>
          <div class="base-info second-base fill-base"></div>
          <div class="base-info third-base"></div>
        </div>
        <div v-if="basePlayers.length === 3 || basePlayers.length === 4">
          <div class="base-info first-base fill-base"></div>
          <div class="base-info second-base fill-base"></div>
          <div class="base-info third-base fill-base"></div>
        </div>
      </div>
      <div>
        <div class="count-container">
          <span class="strike-container">S </span>
          <span v-html="computedStrikeCount"></span>
        </div>
        <div class="count-container">
          <span class="ball-container">B </span>
          <span v-html="computedBallCount"></span>
        </div>
        <div class="count-container">
          <span class="out-container">O </span>
          <span v-html="computedOutCount"></span>
        </div>
      </div>
      <div class="away-container">
        <div class="isOffense" v-if="propsData.awayTeam.isOffense">공격</div>
        <div class="away-team-name team-name">
          {{ propsData.awayTeam.name }}
        </div>
        <div class="away-team-score score">{{ propsData.awayTeam.score }}</div>
      </div>
    </div>
    <div class="flex-item">
      <div>
        <span>투수 : {{ propsData.currentPlayers.pitcher.name }}</span>
        <span> #{{ propsData.currentPlayers.pitcher.pitches }}</span>
      </div>
      <div>
        <span>타자 : {{ propsData.currentPlayers.batter.name }} </span>
        <span>{{ propsData.currentPlayers.batter.mouts }}타석 </span>
        <span>{{ propsData.currentPlayers.batter.hits }}안타</span>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';

export default {
  props: ['propsData'],
  data() {
    return {
      strikeLength: 3,
      ballLength: 4,
      outLength: 3,
    };
  },
  computed: {
    ...mapState(['ballCount', 'basePlayers']),
    computedStrikeCount: function () {
      return this.ballCountHandler(
        this.strikeLength,
        this.$store.state.ballCount.strike,
        // eslint-disable-next-line
        "green",
      );
    },
    computedBallCount: function () {
      return this.ballCountHandler(
        this.ballLength,
        this.$store.state.ballCount.ball,
        // eslint-disable-next-line
        "yellow",
      );
    },
    computedOutCount: function () {
      return this.ballCountHandler(
        this.outLength,
        this.$store.state.ballCount.out,
        // eslint-disable-next-line
        "red",
      );
    },
  },
  methods: {
    // eslint-disable-next-line vue/return-in-computed-property
    ballCountHandler(statusCount, currentCount, color) {
      const countPointList = [];
      for (let i = 1; i < statusCount; i++) {
        if (i <= currentCount) {
          countPointList.push(
            `<div data-v-3cf84859 class="count-point" style="background:${color};"></div>`,
          );
        } else {
          countPointList.push(
            `<div data-v-3cf84859 class="count-point" style="background:#fff;"></div>`,
          );
        }
      }
      return countPointList.join('');
    },
  },
};
</script>

<style scoped>
.flex-container {
  position: absolute;
  width: 390px;
  margin: 10px;
  padding: 5px;
  border-radius: 5px;
  background: #60b99a;
}

.flex-item {
  margin: 0px;
  padding: 20px;
  color: #fff;
  text-align: center;
  /* background: #4584b1; */
  background: #000;
  border: 1px solid #fff;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.flex-item:first-child {
  border-bottom: none;
}

.flex-item:last-child {
  padding: 10px;
}

/* 이닝 정보 */
.inning-info {
  position: relative;
  background-color: #000;
  width: 60px;
  transform: rotate(45deg);
  height: 60px;
}
.base-info {
  position: absolute;
  transform: rotate(0deg);
  background-color: #fff;
  z-index: 1;
  width: 20px;
  height: 20px;
}
.first-base {
  top: 5px;
  right: 6px;
}
.second-base {
  top: 6px;
  right: 34px;
}
.third-base {
  top: 34px;
  right: 34px;
}
.fill-base {
  background-color: #ff0;
}

.isOffense {
  color: red;
  position: absolute;
  top: -12px;
  right: 20px;
  font-size: 13px;
}

.home-container {
  position: relative;
}

.away-container {
  position: relative;
}

.strike-container {
  color: rgb(0, 255, 55);
}

.ball-container {
  color: rgb(255, 238, 0);
}
.out-container {
  color: rgb(255, 0, 0);
}

.count-point {
  display: inline-block;
  width: 15px;
  height: 15px;
  border-radius: 50%;
  margin-left: 3px;
}

.count-container {
  width: 80px;
  text-align: left;
}

.score {
  font-size: 20px;
  font-weight: bold;
  color: orange;
}

.team-name {
  font-size: 20px;
  font-family: cursive;
}
</style>
