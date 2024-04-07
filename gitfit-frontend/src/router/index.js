import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import ProfileView from '../views/ProfileView.vue'
import CreateInstructorComponent from '../components/CreateInstructorComponent.vue'
import { useOwnerStore } from '@/stores/ownerStore'
import { useInstructorStore } from '@/stores/instructorStore'
import { useCustomerStore } from '@/stores/customerStore'
import { defineStore } from 'pinia'
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
        path: '/login',
        name: 'login',
        component: LoginView
    },
    {
        path: '/profile',
        name: 'profile',
        component: ProfileView
    },
    {
        path: '/createinstructor',
        name: 'createinstructor',
        component: CreateInstructorComponent
    },
    // {
    //   path: '/about',
    //   name: 'about',
    //   // route level code-splitting
    //   // this generates a separate chunk (About.[hash].js) for this route
    //   // which is lazy-loaded when the route is visited.
    //   component: () => import('../views/AboutView.vue')
    // },
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
    // {
    //   path: '/about',
    //   name: 'about',
    //   // route level code-splitting
    //   // this generates a separate chunk (About.[hash].js) for this route
    //   // which is lazy-loaded when the route is visited.
    //   component: () => import('../views/AboutView.vue')
    // }
  ]
});



router.beforeEach((to, from, next) => {
    console.log("inside router before each");
    console.log(localStorage.getItem('userType'));
    //console.log(isLoggedIn());
    if (isLoggedIn() || to.name==='login') {
        next();
    }
    else {
        next({ name: 'login' });
    }
    //   // Check if user is authenticated
    //   if (!isLoggedIn()) {
    //     // Redirect to login page if not authenticated
    //     next({ name: 'login' });
    //   } else {
    //     // Proceed to the route
    //     next();
    //   }
  })
  
  function isLoggedIn() {
    const ownerStore = useOwnerStore();
    const instructorStore = useInstructorStore();
    const customerStore = useCustomerStore();
    console.log("++++++++++++")
    // console.log(customerStore.customer.username);
    console.log(localStorage.getItem('userType'));
    if (localStorage.getItem('userType') === 'Owner') {
        try {
            const response = ownerStore.fetchAndSetOwner();
            return true;
        }   catch (error) {
            localStorage.clear();
            return false;
        }
    } else if (localStorage.getItem('userType') === 'Instructor') {
        try {
            const instructor = localStorage.getItem('instructor');
            const response = instructorStore.fetchAndSetInstructor(instructor.username);
            return true;
        }   catch (error) {
            localStorage.clear();
            return false;
        }
    } else if (localStorage.getItem('userType') === 'Customer') {
        try {
            console.log("REACHED HERE");
            const customer = JSON.parse(localStorage.getItem('customer'));
            console.log(customer);
            const response = customerStore.fetchAndSetCustomer(customer.username);
            return true;
        }   catch (error) {
            localStorage.clear();
            console.log("ERROR");
            console.log(error);
            return false;
        }
    } else {
        localStorage.clear();
        return false;
    }
  }

export default router;
