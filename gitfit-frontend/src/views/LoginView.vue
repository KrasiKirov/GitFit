<script setup>
import SigninComponent from '../components/SigninComponent.vue';
import SignupComponent from '../components/SignupComponent.vue';
import CreateInstructorComponent from '../components/CreateInstructorComponent.vue';
import CustomerAccountDetails from '../components/CustomerAccountDetails.vue';
import BillingComponent from '../components/BillingComponent.vue';
</script>

<template>
    <!-- <BillingComponent /> -->
    <!-- <CustomerAccountDetails /> -->
    <!-- <CreateInstructorComponent />  -->
  <div v-if = "showSignup">
    <SignupComponent @updateForm='updateForm' />
  </div>
  <div v-else>
    <SigninComponent @updateForm='updateForm' />
  </div>

</template>
  

<script>
import { emitter } from '../main';


export default {
    inject: ['emitter'],
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
    }
};
</script>
