import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../views/Home.vue'),
    },
    {
      path: '/signals',
      name: 'signals',
      component: () => import('../views/Signals.vue'),
    },
    {
      path: '/drafts',
      name: 'drafts',
      component: () => import('../views/Drafts.vue'),
    },
    {
      path: '/intel',
      name: 'intel',
      component: () => import('../views/Intel.vue'),
    },
  ],
})

export default router
