<template>
  <div class="warp">
    <div class="title-container">
      <h2>BASEBALL GAME ONLINE</h2>
    </div>
    <div class="team-container">
      <div v-for="team in matchArr" :key="team.id" class="match-container">
        <span @click="onClickSelectTeam">{{ team.home }}</span> vs
        <span @click="onClickSelectTeam">{{ team.away }}</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      matchArr: [
        {
          id: 1,
          home: '삼성',
          away: '두산',
        },
        {
          id: 2,
          home: '한화',
          away: '넥센',
        },
        {
          id: 3,
          home: '롯데',
          away: '기아',
        },
        {
          id: 4,
          home: 'NC',
          away: 'SK',
        },
      ],
    };
  },
  methods: {
    onClickSelectTeam: function ({ target: { innerText } }) {
      const matchObj = this.matchArr.find(
        el => el.home == innerText || el.away == innerText,
      );
      // console.log(matchObj);
      this.$router.push(`main/${innerText}`);
    },
  },
};
</script>

<style scoped></style>
