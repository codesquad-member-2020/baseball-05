<template>
  <div class="contained-table">
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
</template>

<script>
import { fetchScores } from '@/api/game';

export default {
  data() {
    return {
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
      console.log(data);
      this.inningInfo = data;
    },
  },
};
</script>

<style>
body {
  background: url('https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/Angels_Stadium.JPG/1200px-Angels_Stadium.JPG')
    center no-repeat;
  background-size: cover;
  min-height: 100vh;
}

.contained-table {
  background-color: #fff;
  border: 1px solid #eee;
  margin: 0.5em;
  padding: 0.3em;
}

table {
  border-collapse: collapse;
  font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
}

caption {
  font-size: 4vw;
  font-weight: bold;
  padding: 0.8em;
}

table,
th,
tr,
td {
  padding: 0.5em 0.75em;
  color: #000;
  text-align: center;
}

th,
[data-th]:first-child {
  background-color: #eee;
}

[data-th='Team'] {
  color: #134a8e;
  font-weight: bold;
  /* display: flex; */
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
  color: #134a8e;
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
</style>
