import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

const router = new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/',
      redirect: '/login',
    },
    {
      path: '/login',
      component: () => import('@/views/LoginView.vue'),
    },
    {
      path: '/select',
      component: () => import('@/views/SelectTeamView.vue'),
    },
    {
      path: '/main/:id',
      component: () => import('@/views/GameView.vue'),
    },
  ],
});

export default router;
