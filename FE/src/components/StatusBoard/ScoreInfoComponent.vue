<template>
  <div>
    <div
      class="score-toggle-container"
      @mouseover="toggleContainer"
      @mouseleave="toggleContainer"
    ></div>
    <div
      class="contained-table"
      :class="{ fadeIn: this.toggleValue, fadeOut: !this.toggleValue }"
    >
      <div v-if="this.inningInfo.length === 0">loading...</div>
      <table v-else>
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
              <span class="long">{{ this.inningInfo[0].team }}</span>
            </td>
            <td data-th="Total">{{ this.inningInfo[0].totalScore }}</td>
            <td
              v-for="score in this.inningInfo[0].inningScore"
              :key="score.index"
            >
              {{ score }}
            </td>
          </tr>
          <tr>
            <td data-th="Team">
              <img
                src="//www.mlbstatic.com/mlb.com/builds/site-core/6105139ccce70320c674a1c11cbdf1e9d88bfe14_1532366495/images/logos/team-cap-on-light/142.svg"
              />
              <span class="long">{{ this.inningInfo[1].team }}</span>
            </td>
            <td data-th="Total">{{ this.inningInfo[1].totalScore }}</td>
            <td
              v-for="score in this.inningInfo[1].inningScore"
              :key="score.index"
            >
              {{ score }}
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import { fetchScores } from '@/api/game';

export default {
  data() {
    return {
      toggleValue: false,
      innningList: [
        'Team',
        'Total',
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
      ],
      inningInfo: [],
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      const { data } = await fetchScores();
      this.inningInfo = data;
    },
    toggleContainer() {
      this.toggleValue = !this.toggleValue;
    },
  },
};
</script>

<style scoped>
.contained-table {
  position: absolute;
  top: -50%;
  right: 20%;
  background-color: #00000099;
  border: 1px solid #eee;
  margin: 0.5em;
  padding: 0.3em;
  animation-duration: 1s;
  animation-fill-mode: both;
}

.score-toggle-container {
  position: absolute;
  width: 100%;
  background: #ffffff00;
  z-index: 1;
  height: 15%;
  top: -92px;
}

table {
  border-collapse: collapse;
  font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
  outline: 1px solid #fff;
}

table,
th,
tr,
td {
  padding: 0.5em 0.75em;
  color: #fff;
  text-align: center;
}

[data-th='Team'] {
  /* color: #134a8e; */
  color: #fff;
  font-weight: bold;
}

.total-score {
  vertical-align: middle;
}

[data-th='Team'] img {
  max-width: 30px;
  vertical-align: middle;
  padding: 0.3em;
}

[data-th='Total'] {
  font-size: 1.125em;
  font-weight: bold;
  color: red;
}

.long {
  display: block;
}

@media screen and (max-width: 300px) {
  caption {
    font-size: 1em;
  }
  /* hide detailed columns */
  th:not(:first-child):not(:last-child),
  td:not(:first-child):not(:last-child) {
    display: none;
  }
  td {
    padding: 1.5em;
  }
}

@media screen and (min-width: 301px) and (max-width: 450px) {
  /* vertical display */
  table,
  thead,
  tbody,
  th,
  tr,
  td {
    display: block;
  }
  thead tr {
    position: absolute;
    top: -9999px;
    left: -9999px;
  }
  td {
    position: relative;
    padding-left: 50%;
  }
  /* add row labels */
  td:before {
    position: absolute;
    left: 6px;
    content: attr(data-th);
    font-weight: bold;
  }
}

@media screen and (min-width: 451px) {
  .long {
    display: block;
  }
  /* horizontal scroll */
  .contained-table {
    margin: 1em auto;
    max-width: 50em;
    overflow-x: auto;
  }
  table {
    margin: 1.5em auto;
    max-width: 100%;
  }
}

.fadeIn {
  animation-name: fadeIn;
}

.fadeOut {
  animation-name: fadeOut;
}

@keyframes fadeIn {
  0% {
    top: -50%;
  }
  100% {
    top: -10%;
  }
}

@keyframes fadeOut {
  0% {
    top: -10%;
  }
  100% {
    top: -50%;
  }
}
</style>
