<script setup>
import ErrorModal from '@/components/ErrorModal.vue';
import SessionCard from '@/components/SessionCard.vue';
import SessionCreationSuccessModal from '@/components/SessionCreationSuccessModal.vue';
import { useRegistrationStore } from '@/stores/registrationStore';
import { useSessionStore } from '@/stores/sessionStore';
import { onBeforeMount, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const registrationStore = useRegistrationStore();
const sessionStore = useSessionStore();
const router = useRouter();
const route = useRoute();
const showSuccessModal = ref(false);
const showModal = ref(false);
const errorMessage = ref('');
const message = ref('Session registered successfully.');
const session = ref(null);
const customer = ref({
    "username": "john_smith",
});

const registrationData = ref({
    "date": `${new Date().toISOString().slice(0, 10)}`,
    "sessionId": `${session.id}`,
    "customerUsername": `${customer.username}`
});

onBeforeMount(async () => {
    const id = route.params.id;
    await sessionStore.fetchAndSetSessionById(id);
    session.value = sessionStore.session;
    // customer.value = customerStore.customer;
    registrationData.value.sessionId = session.value.id;
    registrationData.value.customerUsername = customer.value.username;
});

const handleRegister = async () => {
    try {
        const response = await registrationStore.createRegistration(registrationData.value);
        if (response) {
            console.log("Registration successful");
            showSuccessModal.value = true;
            setTimeout(() => {
                closeSuccessModalAndRedirect();
            }, 2000); // Wait for 2 seconds before redirecting
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
        <button @click="handleRegister(registrationData)" type="button"
            class="mt-10 mx-auto px-4 py-2 bg-persianblue text-white font-semibold rounded-md hover:bg-moodyblue focus:outline-none focus:ring-2 focus:ring-spindle focus:ring-offset-2 transition-colors">
            Register
        </button>
        <ErrorModal :show="showModal" :message="errorMessage" @update:show="showModal = $event" />
        <!-- TODO: rename modal -->
        <SessionCreationSuccessModal :show="showSuccessModal" :message="message"
            @update:show="closeSuccessModalAndRedirect" />
    </div>
</template>