<template>
  <div>
    <game-standby-component></game-standby-component>
  </div>
</template>

<script>
import GameStandbyComponent from '@/components/Main/GameStandbyComponent';

export default {
  components: {
    GameStandbyComponent,
  },
};
</script>

<style scoped>
#app {
  background: #000;
}
</style>
