<template>
  <div class="warp">
    <div class="title-container">
      <h2>BASEBALL GAME ONLINE</h2>
    </div>
    <h2>참가할 게임을 선택하세요!</h2>
    <div v-if="!this.matchList" class="list-container loading-container">
      {{ 'loading...' }}
    </div>
    <div v-else class="list-container">
      <div
        class="team-container"
        v-for="(team, index) in matchList"
        :key="team.index"
      >
        <button class="match-container" :class="{ active: !team.selectable }">
          <div class="game-number">GAME{{ index + 1 }}</div>
          <span class="user-name">{{ team.homeTeam.userName }}</span>
          <span
            class="home-team team"
            @click="onClickSelectTeam"
            :class="{ isuser: team.homeTeam.userName }"
            >{{ team.homeTeam.teamName }}
          </span>
          vs
          <span
            class="away-team team"
            :class="{ isuser: team.awayTeam.userName }"
            @click="onClickSelectTeam"
            >{{ team.awayTeam.teamName }}</span
          >
          <span class="user-name">{{ team.awayTeam.userName }}</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import { fetchMatches } from '@/api/game';

export default {
  data() {
    return {
      matchList: [],
      isUser: false,
    };
  },

  computed: {
    ...mapState(['matchesList']),
  },

  created() {
    this.syncData();
  },

  methods: {
    async fetchData() {
      const { data } = await fetchMatches();
      console.log(data);
      this.matchList = data;
    },

    syncData() {
      setTimeout(() => {
        this.fetchData();
        this.syncData();
      }, 3000);
    },

    onClickSelectTeam: function ({ target: { innerText } }) {
      const matchObj = this.matchList.find(
        el => el.home == innerText || el.away == innerText,
      );
      if (
        this.matchList.homeTeam.userName &&
        this.matchList.awayTeam.userName
      ) {
        alert('이미 선택된 팀입니다.');
        return;
      }

      this.$router.push(`main/${innerText}`);
    },
  },
};
</script>

<style scoped>
.warp {
  padding: 10px;
  background-color: #00000080;
  color: #fff;
}

.game-number {
  font-size: 15px;
}

.title-container > h2 {
  font-size: 34px;
}

.loading-container {
  color: red;
}

.match-container {
  background-color: #ffffff80;
  margin: 15px auto;
  display: block;
  width: 400px;
  padding: 10px;
  font-size: 24px;
}

.list-container {
  overflow: auto;
  height: 220px;
}

.list-container::-webkit-scrollbar {
  display: none;
}

.active {
  background-color: #ff0000;
  color: #fff;
  pointer-events: none;
  position: relative;
}

.active::after {
  position: absolute;
  left: 10px;
  top: 10px;
  font-size: 15px;
  content: '게임중';
  color: #fff;
}

.team {
  margin: 0 10px;
}

.team:hover {
  color: red;
}

.user-name {
  font-size: 15px;
}

.isuser {
  color: chartreuse;
  pointer-events: none;
}
</style>
