<template>
    <div class="flex min-h-full flex-1 flex-col justify-center px-6 py-12 lg:px-8">
      <div class="sm:mx-auto sm:w-full sm:max-w-sm">
        <h2 class="mt-32 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Sign in to your account</h2>
      </div>
  
      <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
        <form class="space-y-6" action="#" method="POST">
          <div>
            <label for="signinUsername" class="block text-sm font-medium leading-6 text-gray-900">Username</label>
            <div class="mt-2">
              <input id="signinUsername" name="signinUsername" type="email" autocomplete="email" required="" class="p-2 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-custom-blue focus:outline-none sm:text-sm sm:leading-6" />
            </div>
          </div>
  
          <div>
            <div class="flex items-center justify-between">
              <label for="signinPassword" class="block text-sm font-medium leading-6 text-gray-900">Password</label>
            </div>
            <div class="mt-2">
              <input id="signinPassword" name="signinPassword" type="password" autocomplete="current-password" required="" class="p-2 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-custom-blue focus:outline-none sm:text-sm sm:leading-6" />
            </div>
          </div>
  
          <div>
            <button type="submit" class="mt-10 flex w-full justify-center rounded-md bg-custom-blue px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-custom-dark-blue focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">Sign in</button>
          </div>
        </form>
  
        <p class="mt-5 text-center text-sm text-gray-500">
          Don't have an account?
          {{ ' ' }}
          <a href="#" class="font-semibold leading-6 text-custom-blue hover:text-custom-dark-blue" @click="updateForm">Sign up!</a>
        </p>
      </div>
    </div>
  </template>

<script>
export default {
    data() {
        return {
            username: '',
            password: ''
        };
    },
    methods: {
        updateForm() {
            this.$emit('updateForm');
        },
        login() {
            // Perform login logic here
            // Example: make an API call to authenticate the user
            // and redirect to the dashboard on success
        }
    }
}
</script>