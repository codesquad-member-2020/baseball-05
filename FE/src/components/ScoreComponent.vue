<template>
  <div v-if="!propsData.homeTeam"></div>
  <div v-else>
    <div class="home-score info">{{ propsData.homeTeam.score }}</div>
    <div class="inning info">{{ propsData.inning.id }}</div>
    <div class="away-score info">{{ propsData.awayTeam.score }}</div>
  </div>
</template>

<script>
export default {
  props: ['propsData'],
};
</script>

<style scoped>
.info {
  color: gold;
  position: absolute;
  font-size: 14px;
}

.home-score {
  top: 118px;
  left: 569px;
}

.inning {
  top: 118px;
  left: 601px;
}

.away-score {
  top: 118px;
  right: 558px;
}
</style>
