<template>
  <div class="player-container">
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
</template>

<script>
import { fetchRoters } from '@/api/game';

export default {
  data() {
    return {
      playerInfo: [],
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      const { data } = await fetchRoters();
      console.log(data);
      this.playerInfo = data;
    },
  },
};
</script>

<style scoped>
.player-container {
  display: flex;
  background-color: #000;
  margin: 0 auto;
  width: 800px;
  padding: 20px;
}

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
</style>
