<template>
  <div>
    <div class="batter-info">
      <span>S <span class="strike-number">🟢🟢</span></span>
      <span>B <span class="strike-number">🟡🟡🟡</span></span>
      <span>O <span class="strike-number">🔴🔴</span></span>
    </div>
    <div class="base-info"></div>
    <div class="inning-info">
      <span>{{ matchArr.inningMeaage }}</span>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
export default {
  computed: {
    ...mapState(['matchArr']),
    countImage: () => {},
  },
};
</script>

<style></style>
