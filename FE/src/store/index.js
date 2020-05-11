import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    currentBatter: [],
    basePlayers: [],
    matchesList: [],
    matchArr: {
      inningMeaage: '2회초 공격',
      inning: {
        firstHalf: 2,
        secondHalf: 2,
      },
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
    plate: [
      {
        id: 1,
        plate: 7,
        batter: {
          name: '김광진',
          mount: 1,
          hit: 0,
          pitcher: false,
        },
        rounds: [
          {
            decision: '스트라이크',
            strike: 1,
            ball: 0,
          },
          {
            decision: '볼',
            strike: 1,
            ball: 1,
          },
          {
            decision: '볼',
            strike: 1,
            ball: 2,
          },
          {
            decision: '볼',
            strike: 1,
            ball: 3,
          },
          {
            decision: '스트라이크',
            strike: 2,
            ball: 3,
          },
          {
            decision: '안타!',
            strike: 0,
            ball: 0,
          },
        ],
      },
      {
        id: 2,
        plate: 7,
        batter: {
          name: '김광진',
          mount: 1,
          hit: 0,
          pitcher: false,
        },
        rounds: [
          {
            decision: '스트라이크',
            strike: 1,
            ball: 0,
          },
          {
            decision: '볼',
            strike: 1,
            ball: 1,
          },
          {
            decision: '볼',
            strike: 1,
            ball: 2,
          },
          {
            decision: '볼',
            strike: 1,
            ball: 3,
          },
          {
            decision: '스트라이크',
            strike: 2,
            ball: 3,
          },
          {
            decision: '안타!',
            strike: 0,
            ball: 0,
          },
        ],
      },
      {
        id: 3,
        plate: 7,
        batter: {
          name: '김광진',
          mount: 1,
          hit: 0,
          pitcher: false,
        },
        rounds: [
          {
            decision: '스트라이크',
            strike: 1,
            ball: 0,
          },
          {
            decision: '볼',
            strike: 1,
            ball: 1,
          },
          {
            decision: '볼',
            strike: 1,
            ball: 2,
          },
          {
            decision: '볼',
            strike: 1,
            ball: 3,
          },
          {
            decision: '스트라이크',
            strike: 2,
            ball: 3,
          },
          {
            decision: '안타!',
            strike: 0,
            ball: 0,
          },
        ],
      },
    ],
    test: {
      homeTeam: {
        name: 'Captain',
        score: 1,
        offense: false,
      },
      awayTeam: {
        name: 'Marvel',
        score: 5,
        offense: true,
      },
      pitcher: {
        name: '최동원',
        mount: 39,
        hit: 0,
        pitcher: true,
      },
      inning: {
        inning: 2,
        half: '초',
        offenseOrDefense: '수비',
      },
      plates: [
        {
          plate: 6,
          out: 1,
          batter: {
            name: '김광진',
            mount: 1,
            hit: 0,
            pitcher: false,
          },
          rounds: [
            {
              decision: '스트라이크',
              strike: 1,
              ball: 0,
            },
            {
              decision: '볼',
              strike: 1,
              ball: 1,
            },
            {
              decision: '볼',
              strike: 1,
              ball: 2,
            },
            {
              decision: '볼',
              strike: 1,
              ball: 3,
            },
            {
              decision: '스트라이크',
              strike: 2,
              ball: 3,
            },
            {
              decision: '안타!',
              strike: 0,
              ball: 0,
            },
          ],
        },
        {
          plate: 7,
          out: 1,
          batter: {
            name: '이용대',
            mount: 2,
            hit: 1,
            pitcher: false,
          },
          rounds: [
            {
              decision: '스트라이크',
              strike: 1,
              ball: 0,
            },
            {
              decision: '볼',
              strike: 1,
              ball: 1,
            },
            {
              decision: '스트라이크',
              strike: 2,
              ball: 1,
            },
          ],
        },
      ],
    },
  },
  mutations: {},
  actions: {},
});
