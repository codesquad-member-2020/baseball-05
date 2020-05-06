import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    matchArr: {
      homeTeam: {
        name: 'testA',
        score: 0,
        isOffense: false,
        isUserTeam: true,
        pitcher: {
          name: 'huey',
          round: 0,
        },
        batter: {
          name: '휴이',
          mount: 2,
          hit: 3,
        },
        player: [
          {
            name: 'huey',
            teamName: 'testA',
            mount: 0,
            hit: 0,
            strike: 2,
            ball: 2,
            out: 1,
            battingAverage: 0.231,
          },
          {
            name: 'huey',
            teamName: 'testA',
            mount: 0,
            hit: 0,
            strike: 2,
            ball: 2,
            out: 1,
            battingAverage: 0.231,
          },
        ],
      },
      awayTeam: {
        name: 'testB',
        score: 2,
        isOffense: true,
        isUserTeam: false,
        pitcher: {
          name: 'huey',
          round: 0,
        },
        batter: {
          name: '휴이',
          mount: 2,
          hit: 3,
        },
        player: [
          {
            name: 'huey',
            teamName: 'testA',
            mount: 0,
            hit: 0,
            strike: 2,
            ball: 2,
            out: 1,
            battingAverage: 0.231,
          },
          {
            name: 'huey',
            teamName: 'testA',
            mount: 0,
            hit: 0,
            strike: 2,
            ball: 2,
            out: 1,
            battingAverage: 0.231,
          },
        ],
      },
    },
  },
  getters: {},
  mutations: {},
  actions: {},
});
