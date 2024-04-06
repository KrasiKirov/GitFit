<script setup>
import SessionCard from '@/components/SessionCard.vue';
import ErrorModal from '@/components/ErrorModal.vue';
import SessionCreationSuccessModal from '@/components/SessionCreationSuccessModal.vue';
import { ref, computed, onMounted } from 'vue';
import { useSessionStore } from '@/stores/sessionStore';
import { useRegistrationStore } from '@/stores/registrationStore';
import { useCustomerStore } from '@/stores/customerStore';
import { useRouter } from 'vue-router';

const sessionStore = useSessionStore();
const customerStore = useCustomerStore();
const registrationStore = useRegistrationStore();
const router = useRouter();
const showSuccessModal = ref(false);
const showModal = ref(false);
const errorMessage = ref('');
const message = ref('Session registered successfully.');

const session = computed(() => sessionStore.session);
const customer = computed(() => customerStore.getCustomer());

const registrationData = ref({
    "date": `${new Date().toISOString().slice(0, 10)}`,
    "sessionId": `${session.id}`,
    "customerUsername": `${customer.username}`
});

const handleRegister = async (registrationData) => {
    console.log(registrationData); // Debugging
    try {
        const response = await registrationStore.createRegistration(registrationData);
        if (response) {
            showSuccessModal.value = true;
        }
    } catch (error) {
        errorMessage.value = error.message;
        showModal.value = true;
    }    
};

const closeSuccessModalAndRedirect = () => {
  showSuccessModal.value = false;
  router.push('/sessions'); // Redirect to sessions page
};

</script>

<template>
    <div class="bg-spindle min-h-screen flex flex-col items-center">
        <SessionCard :session="session">
        </SessionCard>
        <button @click="handleRegister(registrationData)" 
            type="button" class="mt-10 mx-auto px-4 py-2 bg-persianblue text-white font-semibold rounded-md hover:bg-moodyblue focus:outline-none focus:ring-2 focus:ring-spindle focus:ring-offset-2 transition-colors">
            Register
        </button>
        <ErrorModal :show="showModal" :message="errorMessage" @update:show="showModal = $event" />
        <!-- TODO: rename modal -->
        <SessionCreationSuccessModal :show="showSuccessModal" :message="message" @update:show="closeSuccessModalAndRedirect" />
    </div>
</template>