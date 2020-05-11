<template>
  <div class="log-container">
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
.log-container {
  color: #fff;
  position: absolute;
  right: 0px;
  height: 500px;
  top: 15%;
  width: 210px;
  background: #000000;
  outline: 1px solid #fff;
  overflow: auto;
  padding: 20px;
}

.log-container::-webkit-scrollbar {
  display: none;
}

.current-batter {
  color: red;
}
.log-batter {
  color: rgb(0, 68, 255);
}
</style>
