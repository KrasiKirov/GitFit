<template>
    <div class="flex min-h-full flex-col justify-center items-center ">
        <div class="flex flex-col min-h-screen items-center">
        <div class="sm:mx-auto sm:w-full sm:max-w-sm">
            <h2 class="mt-20 mb-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">User Profile</h2>
        </div>
        <div class="w500 bg-white overflow-hidden shadow rounded-lg border mb-10">
        
        <div class="border-t border-gray-200 px-4 py-5 sm:p-0">
            <dl class="sm:divide-y sm:divide-gray-200">
                <div class="flex px-4 py-5 sm:px-6">
                <h3 class="text-lg leading-6 font-medium text-gray-900">
                    Basic Information
                </h3>
            </div>
                <div class="py-3 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
                    <dt class="text-sm font-medium text-gray-500">
                        First name
                    </dt>
                    <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                        {{ customer.firstName }}
                    </dd>
                </div>
                <div class="py-3 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
                    <dt class="text-sm font-medium text-gray-500"> 
                        Last name
                    </dt>
                    <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                        {{ customer.lastName }}
                    </dd>
                </div>
                <div class="py-3 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
                    <dt class="text-sm font-medium text-gray-500">
                        Email
                    </dt>
                    <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                        {{ customer.email }}
                    </dd>
                </div>
                <div class="py-3 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
                    <dt class="text-sm font-medium text-gray-500">
                        Username
                    </dt>
                    <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                        {{ customer.username }}
                    </dd>
                </div>
                <div class="flex px-4 py-5 sm:px-6">
                <h3 class="text-lg leading-6 font-medium text-gray-900">
                    Login Information
                </h3>
                <div class="flex px-8 items-center cursor-pointer" @click="editPassword">
                    <img src="../assets/edit.png" alt="edit" class="w-4 h-4">
                    <div class="px-1">Edit</div>
                </div>
            </div>
                <div class="py-3 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
                    <dt class="text-sm font-medium text-gray-500">
                        Password
                    </dt>
                    <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                        <b>••••••••</b>
                    </dd>
                </div>
                <div class="flex px-4 py-5 sm:px-6">
                <h3 class="text-lg leading-6 font-medium text-gray-900">
                    Billing Information
                </h3>
                <div class="flex px-8 items-center cursor-pointer" @click="editBilling">
                    <img src="../assets/edit.png" alt="edit" class="w-4 h-4">
                    <div class="px-1">Edit</div>
                </div>
            </div>
                <div class="py-3 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
                    <dt class="text-sm font-medium text-gray-500">
                        Card
                    </dt>
                    <dd v-if="billing" class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                        **** **** **** {{ billing.cardNumberEnd }}
                    </dd>
                    <dd v-else class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                        No card on file
                    </dd>
                    
                </div>
            </dl>
        </div>
    </div>
    </div>
    </div>
</template>

<script setup>
import { onMounted, computed } from 'vue';
import { useCustomerStore } from '@/stores/customerStore';
import { useBillingStore } from '@/stores/billingStore';
import { defineEmits, ref } from 'vue';

const emit = defineEmits(['editBilling', 'editPassword']);
const customerStore = useCustomerStore();
const billingStore = useBillingStore();
// onMounted(() => {
//     customerStore.updateCustomerFromLocalStorage();
//     // billingStore.getBilling();
// });

const customer = computed(() => customerStore.customer);
const billing = computed(() => billingStore.billing);

const editBilling = () => {
    emit('editBilling');
};

const editPassword = () => {
    emit('editPassword');
};

</script>