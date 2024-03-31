<script setup>
import SigninComponent from '../components/SigninComponent.vue';
import SignupComponent from '../components/SignupComponent.vue';
import CreateInstructorComponent from '../components/CreateInstructorComponent.vue';
import CustomerAccountDetails from '../components/CustomerAccountDetails.vue';
import BillingComponent from '../components/BillingComponent.vue';
</script>

<template>
    <BillingComponent />
    <!-- <CustomerAccountDetails /> -->
    <!-- <CreateInstructorComponent />  -->
  <!-- <div v-if = "showSignup">
    <SignupComponent @updateForm='updateForm'/>
  </div>
  <div v-else>
    <SigninComponent @updateForm='updateForm'/>
  </div> -->

</template>
  

<script>
export default {
    data() {
        return {
            email: '',
            password: '',
            showSignup: false,
            signupEmail: '',
            signupPassword: ''
        };
    },
    methods: {
      updateForm() {
        this.showSignup = !this.showSignup;
      },
      login() {
          // Perform login logic here
          // Example: make an API call to authenticate the user
          // and redirect to the dashboard on success
          // You can use a library like axios for making API calls
          // axios.post('/api/login', { email: this.email, password: this.password })
          //   .then(response => {
          //     if (response.data.success) {
          //       this.$router.push('/dashboard');
          //     } else {
          //       // Handle login error
          //     }
          //   })
          //   .catch(error => {
          //     // Handle API error
          //   });
      },
      showSignupForm() {
          this.showSignup = true;
      },
      showLoginForm() {
          this.showSignup = false;
      },
      signup() {
          // Perform signup logic here
          // Example: make an API call to create a new user
          // axios.post('/api/signup', { email: this.signupEmail, password: this.signupPassword })
          //   .then(response => {
          //     if (response.data.success) {
          //       // Handle successful signup
          //     } else {
          //       // Handle signup error
          //     }
          //   })
          //   .catch(error => {
          //     // Handle API error
          //   });
      }
    }
};
</script>
