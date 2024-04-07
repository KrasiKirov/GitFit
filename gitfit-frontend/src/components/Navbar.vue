<template>
    <nav class="bg-white border-gray-200 dark:bg-gray-900 sticky top-0">
        <div class="flex flex-wrap items-center justify-between p-4">
            <router-link to="/" class="flex items-center space-x-3 rtl:space-x-reverse">
                <img src="@/assets/gradlesus.svg" class="h-8" alt="Flowbite Logo" />
                <span class="self-center text-2xl font-semibold whitespace-nowrap dark:text-white">GitFit</span>
            </router-link>
            <!-- ... -->
            <div class="hidden w-full md:block md:w-auto" id="navbar-default">
                <ul
                    class="font-medium flex flex-col p-4 md:p-0 mt-4 border border-gray-100 bg-gray-50 md:flex-row md:space-x-8 rtl:space-x-reverse md:mt-0 md:border-0 md:bg-white dark:bg-gray-800 md:dark:bg-gray-900 dark:border-gray-700">
                    <li>
                        <router-link to="/"
                            class="block py-2 px-3 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent">Home</router-link>
                    </li>
                    <li>
                        <router-link to="/about" class="block py-2 px-3 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent
                            md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500
                            dark:hover:bg-gray-700 dark:hover:text-white
                            md:dark:hover:bg-transparent">About</router-link>
                    </li>
                    <li><!-- /registrations -->
                        <router-link to="/registrations" class="block py-2 px-3 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent
                            md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500
                            dark:hover:bg-gray-700 dark:hover:text-white
                            md:dark:hover:bg-transparent">Registrations</router-link>
                    </li>
                    <li>
                        <router-link to="/sessions" class="block py-2 px-3 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent
                            md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500
                            dark:hover:bg-gray-700 dark:hover:text-white
                            md:dark:hover:bg-transparent">Sessions</router-link>
                    </li>
                    <li v-if="userType!='Customer'">
                        <router-link to="/new-session" class="block py-2 px-3 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent
                            md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500
                            dark:hover:bg-gray-700 dark:hover:text-white
                            md:dark:hover:bg-transparent">New Session</router-link>
                    </li>
                    <li>
                        <router-link to="/fitness-classes" class="block py-2 px-3 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent
                            md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500
                            dark:hover:bg-gray-700 dark:hover:text-white
                            md:dark:hover:bg-transparent">Fitness Classes</router-link>
                    </li>
                    <li v-if="userType!='Customer'">
                        <router-link to="/new-fitness-class" class="block py-2 px-3 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent
                            md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500
                            dark:hover:bg-gray-700 dark:hover:text-white
                            md:dark:hover:bg-transparent">New Fitness Class</router-link>
                    </li>
                    <li v-if="userType!='Customer'">
                        <router-link to="/instructors" class="block py-2 px-3 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent
                            md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500
                            dark:hover:bg-gray-700 dark:hover:text-white
                            md:dark:hover:bg-transparent">Instructors</router-link>
                    </li>
                    <li>
                        <div class="block py-2 px-3 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent
                            md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500
                            dark:hover:bg-gray-700 dark:hover:text-white
                            md:dark:hover:bg-transparent" @click="account">Account</div>
                    </li>
                    <li @click="logout">
                        <div
                            class="block py-2 px-3 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent">Sign
                            Out</div>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</template>

<script setup>
import router from '@/router';
import { ref, onMounted, onBeforeUnmount} from 'vue';

const userType = ref(localStorage.getItem('userType') || '');

const updateUserType = (event) => {
  if (event.key === 'userType') {
    const newValue = localStorage.getItem('userType');
    userType.value = newValue;
  }
};

onMounted(() => {
  // Retrieve userType from localStorage
  userType.value = localStorage.getItem('userType');

  // Watch for changes in userType in localStorage
  window.addEventListener('storage', updateUserType);
});

onBeforeUnmount(() => {
  // Clean up event listener when component is destroyed
  window.removeEventListener('storage', updateUserType);
});

const logout = () => {
    console.log("Logging out");
    localStorage.clear();
    router.push('/login');
};

const account = () => {
    router.push('/profile');
};

</script>