import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import CreateSessionView from '../views/CreateSessionView.vue'
import CreateFitnessClassView from '../views/CreateFitnessClassView.vue'
import InstructorManagementView from '../views/InstructorManagementView.vue'
import FitnessClassManagementView from '../views/FitnessClassManagementView.vue'
import RegistrationView from '../views/RegistrationView.vue';
import SportCenterManagementView from '../views/SportCenterManagementView.vue';




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
      path: '/sessions',
      name: 'sessions',
      component: () => import('../views/SessionsView.vue')
    },
    {
      path: '/sessions/:id',
      name: 'SessionDetails',
      component: () => import('../views/SingleSessionView.vue')
    },
    {
      path: '/InstructorManagementView',
      path: '/new-session',
      name: 'SessionCreation',
      component: CreateSessionView
    },
    {
      path: '/new-fitness-class',
      name: 'FitnessClasssCreation',
      component: CreateFitnessClassView
    },
    {
      path: '/instructors',
      name: 'InstructorManagement',
      component: InstructorManagementView
    },
    {
      path: '/fitness-classes',
      name: 'FitnessClassManagement',
      component: FitnessClassManagementView
    },
    {
      path: "/registrations",
      name: "registrations",
      component: RegistrationView
    },
    {
      path: '/about',
      name: 'about',
      component: SportCenterManagementView
    }
  ]
});

export default router;
