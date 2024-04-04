<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import ErrorModal from '@/components/ErrorModal.vue';
import SuccessModal from '@/components/SuccessModal.vue';
import SessionCreationForm from '@/components/SessionCreationForm.vue';
import { useSessionStore } from '@/stores/sessionCreationStore';

const router = useRouter();


const sessionStore = useSessionStore();
const showModal = ref(false);
const showSuccessModal = ref(false);
const errorMessage = ref('');
const message = ref('Successfully created session.');

const handleSessionCreation = async (sessionData) => {
  try {
    const response = await sessionStore.createSession(sessionData);
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
  router.push('/'); // Redirect to home page
};

</script>

<template>
  <div>
    <SessionCreationForm @create-session="handleSessionCreation" />
    <ErrorModal :show="showModal" :message="errorMessage" @update:show="showModal = $event" />
    <SuccessModal :show="showSuccessModal" :message="message" @update:show="closeSuccessModalAndRedirect" />
  </div>
</template>