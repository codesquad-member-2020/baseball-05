<template>
  <div class="ground-container">
    <ground-component></ground-component>
    <play-button></play-button>
  </div>
</template>

<script>
import GroundComponent from '@/components/GroundComponent';
import PlayButton from '@/components/PlayButton';

export default {
  components: {
    GroundComponent,
    PlayButton,
  },
};
</script>

<style>
#app {
  background: #000;
}

.ground-container {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 1200px;
  height: 700px;
  background: url('https://s3-us-west-2.amazonaws.com/mfbrowndesign.com/img/baseball+diamond.jpg')
    no-repeat;
  background-size: 100% 100%;
}
</style>
