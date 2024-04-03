<template>
    <div v-if = "view === 'account'">
        <CustomerAccountDetails @editBilling="editBilling" />
    </div>
    <div v-else-if = "view === 'billing'">
        <BillingComponent @editAccount="editAccount"/>
    </div>
    <!-- <div v-else-if = "view === 'password'">
        <CustomerAccountDetails />
    </div> -->
</template>


<script setup>
import BillingComponent from '../components/BillingComponent.vue';
import CustomerAccountDetails from '@/components/CustomerAccountDetails.vue';
import { ref } from 'vue';
const view = ref("account");

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