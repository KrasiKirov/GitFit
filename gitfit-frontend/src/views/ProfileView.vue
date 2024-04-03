<template>
    <div v-if = "view === 'customerAccountDetails'">
        <CustomerAccountDetails @editBilling="editBilling" />
    </div>
    <div v-else-if = "view === 'billing'">
        <BillingComponent />
    </div>
    <!-- <div v-else-if = "view === 'password'">
        <CustomerAccountDetails />
    </div> -->
</template>


<script setup>
import BillingComponent from '../components/BillingComponent.vue';
import CustomerAccountDetails from '@/components/CustomerAccountDetails.vue';
import { ref } from 'vue';
const view = ref("customerAccountDetails");

const editBilling = () => {
    view.value = "billing";
}

const editAccount = () => {
    view.value = "account";
}

const editPassword = () => {
    view.value = "password";
}

</script>