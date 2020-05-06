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
      },
      awayTeam: {
        name: 'testB',
        score: 2,
        isOffense: true,
        isUserTeam: false,
      },
    },
  },
  getters: {},
  mutations: {},
  actions: {},
});
