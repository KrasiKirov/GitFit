<script setup>
import SessionCard from '@/components/SessionCard.vue';
import { ref, computed } from 'vue';
import { useSessionStore } from '@/stores/sessionStore';
import { useRegistrationStore } from '@/stores/registrationStore';
import { useCustomerStore } from '@/stores/customerStore';

const sessionStore = useSessionStore();
const customerStore = useCustomerStore();
const registrationStore = useRegistrationStore();

const session = computed(() => sessionStore.session);
const customer = computed(() => customerStore.customer);

const registrationData = ref({
    date: `${new Date().toISOString().slice(0, 10)}`,
    sessionId: `${session.id}`,
    customerUsername: `${customer.username}`
});

const handleRegister = async (registrationData) => {
    const response = await registrationStore.createRegistration(registrationData);
};

</script>

<template>
    <div class="bg-spindle min-h-screen flex flex-col items-center">
        <SessionCard :session="session">
        </SessionCard>
            <button @click="handleRegister(registrationData.value)" 
            type="button" class="mt-10 mx-auto px-4 py-2 bg-persianblue text-white font-semibold rounded-md hover:bg-moodyblue focus:outline-none focus:ring-2 focus:ring-spindle focus:ring-offset-2 transition-colors">
            Register
            </button>
    </div>
</template>