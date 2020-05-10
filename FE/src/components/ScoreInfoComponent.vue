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
}

th,
[data-th]:first-child {
  background-color: #eee;
}

[data-th='Team'] {
  color: #134a8e;
  font-weight: bold;
}

[data-th='Team'] img {
  max-width: 30px;
  vertical-align: middle;
  padding: 0.3em;
}

[data-th='Final'] {
  font-size: 1.125em;
  font-weight: bold;
}

.long {
  display: none;
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
  .short {
    display: none;
  }
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
