<template>
  <div class="contained-table">
    <table>
      <thead>
        <tr>
          <td v-for="round in innningList" :key="round">
            {{ round }}
          </td>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td data-th="Team">
            <img
              src="//www.mlbstatic.com/mlb.com/builds/site-core/6105139ccce70320c674a1c11cbdf1e9d88bfe14_1532366495/images/logos/team-primary-on-light/141.svg"
              alt=""
            />
            <span class="short">TOR</span>
            <span class="long">Toronto Blue Jays</span>
          </td>
          <td data-th="1st">0</td>
          <td data-th="2nd">1</td>
          <td data-th="3rd">0</td>
          <td data-th="4th">0</td>
          <td data-th="5th">1</td>
          <td data-th="6th">0</td>
          <td data-th="7th">0</td>
          <td data-th="8th">3</td>
          <td data-th="9th">0</td>
          <td data-th="10th">0</td>
          <td data-th="11th">0</td>
          <td data-th="12th">0</td>
          <td data-th="R">6</td>
        </tr>
        <tr>
          <td data-th="Team">
            <img
              src="//www.mlbstatic.com/mlb.com/builds/site-core/6105139ccce70320c674a1c11cbdf1e9d88bfe14_1532366495/images/logos/team-cap-on-light/142.svg"
            />
            <span class="short">MIN</span>
            <span class="long">Minnesota Twins</span>
          </td>
          <td data-th="1st">0</td>
          <td data-th="2nd">3</td>
          <td data-th="3rd">0</td>
          <td data-th="4th">0</td>
          <td data-th="5th">0</td>
          <td data-th="6th">1</td>
          <td data-th="7th">0</td>
          <td data-th="8th">2</td>
          <td data-th="9th">0</td>
          <td data-th="10th">0</td>
          <td data-th="11th">6</td>
          <td data-th="Final">12</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import { fetchScores } from '@/api/game';

export default {
  data() {
    return {
      innningList: [
        'Team',
        '1st',
        '2nd',
        '3rd',
        '4th',
        '5th',
        '6th',
        '7th',
        '8th',
        '9th',
        '10th',
        '11th',
        '12th',
        'R',
      ],
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      const { data } = await fetchScores();
      console.log(data);
    },
  },
};
</script>

<style></style>
