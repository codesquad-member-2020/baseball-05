<template>
  <div class="loading-container" v-if="this.isLoading">
    <img src="../../assets/BeanEater.svg" alt="" />
  </div>
  <div v-else class="warp">
    <div class="title-container">
      <h2>BASEBALL GAME ONLINE</h2>
    </div>
    <h2>참가할 게임을 선택하세요!</h2>
    <div class="list-container">
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
            data-team="home"
            >{{ team.homeTeam.teamName }}
          </span>
          vs
          <span
            class="away-team team"
            :class="{ isuser: team.awayTeam.userName }"
            @click="onClickSelectTeam"
            data-team="away"
            >{{ team.awayTeam.teamName }}</span
          >
          <span class="user-name">{{ team.awayTeam.userName }}</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import hit from '../../assets/mario.mp3';
import { mapState } from 'vuex';
import { fetchMatches, fetchUser } from '@/api/game';
import axios from 'axios';

export default {
  data() {
    return {
      matchList: [],
      isUser: false,
      isMovePage: false,
      selectTeam: '',
      isLoading: true,
      isSelectUser: '',
    };
  },

  computed: {
    ...mapState(['matchesList']),
  },

  created() {
    this.$confetti.start();
    this.syncData();
  },

  methods: {
    sound() {
      var play = new Audio(hit);
      play.play();
    },
    async fetchData() {
      const { data } = await fetchMatches();
      console.log(data);
      this.matchList = data;
      if (this.matchList && this.isLoading === true) {
        this.isLoading = !this.isLoading;
      }
    },

    async isUserSelected() {
      const obj = { teamName: `${this.selectTeam}` };
      await axios
        .post('http://3.34.15.148/api/games', {
          teamName: `${this.selectTeam}`,
          // 위에 팀네임은 this.teamName으로 수정
        })
        .then(data => {
          this.isSelectUser = data.data;
        });
    },

    syncData() {
      if (this.isMovePage) return;
      setTimeout(() => {
        this.fetchData();
        this.syncData();
      }, 3000);
    },

    async onClickSelectTeam(e) {
      this.sound();
      this.selectTeam = e.target.innerText;
      await this.isUserSelected();
      if (this.isSelectUser === 'fail') {
        alert(' 이미 선택된 팀입니다 ');
        return;
      }
      this.isMovePage = !this.isMovePage;
      this.$router.push(`standby/${e.target.dataset.team}`);
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

.loading-container {
  padding: 10px;
  color: #fff;
}

.game-number {
  font-size: 15px;
}

.title-container > h2 {
  font-size: 34px;
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
