import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import CreateSessionView from '../views/CreateSessionView.vue'
import CreateFitnessClassView from '../views/CreateFitnessClassView.vue'



const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue')
    },
    {
      path: '/SessionCreation',
      name: 'SessionCreation',
      component: CreateSessionView
    },
    {
      path: '/FitnessClassCreation',
      name: 'FitnessClasssCreation',
      component: CreateFitnessClassView
    }
  ]
})

export default router