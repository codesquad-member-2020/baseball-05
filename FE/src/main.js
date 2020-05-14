import Vue from 'vue';
import App from './App.vue';
import router from '@/routes/index';
import store from '@/store/index';
import VueConfetti from 'vue-confetti';

Vue.config.productionTip = false;
Vue.use(VueConfetti);

new Vue({
  VueConfetti,
  router,
  store,
  render: h => h(App),
}).$mount('#app');
