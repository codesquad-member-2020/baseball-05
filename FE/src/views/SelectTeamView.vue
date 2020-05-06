<template>
  <div class="select-container">
    <team-component></team-component>
  </div>
</template>

<script>
import TeamComponent from '@/components/TeamComponent';

export default {
  components: {
    TeamComponent,
  },
};
</script>

<style scoped>
.select-container {
  border: 1px solid #ccc;
  border-radius: 3px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
  margin: 0 auto;
  width: 600px;
}
</style>
