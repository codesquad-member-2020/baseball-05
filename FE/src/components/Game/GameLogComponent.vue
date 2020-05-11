<template>
  <div>
    <div v-for="info in plate" :key="info.id">
      <div v-if="info.id === 1" class="current-batter">
        <span>{{ info.plate }}</span>
        <span>번 타자 </span>
        <span>{{ info.batter.name }}</span>
      </div>
      <div v-else class="log-batter">
        <span>{{ info.plate }}</span>
        <span>번 타자 </span>
        <span>{{ info.batter.name }}</span>
      </div>

      <ul v-for="log in info.rounds.slice().reverse()" :key="log.data">
        <li>
          <span>{{ log.decision }}</span>
          <span v-if="log.decision == '안타!'"> </span>
          <span v-else>
            <span>S{{ log.strike }}</span>
            <span>B{{ log.ball }}</span>
          </span>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';

export default {
  computed: {
    ...mapState(['plate']),
  },
};
</script>

<style scoped>
.current-batter {
  color: red;
}
.log-batter {
  color: rgb(64, 81, 128);
}
</style>
