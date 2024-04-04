<script setup>
import { ref } from 'vue';
import ErrorModal from '@/components/ErrorModal.vue';
import SuccessModal from '@/components/SuccessModal.vue';
import SessionCreationForm from '@/components/SessionCreationForm.vue';
import { useSessionStore } from '@/stores/sessionCreationStore';


const sessionStore = useSessionStore();
const showModal = ref(false);
const showSuccessModal = ref(false);
const errorMessage = ref('');
const message = ref('');

const handleSessionCreation = async (sessionData) => {
  try {
    const response = await sessionStore.createSession(sessionData);
    if (response && response.status === 200) {
      message.value = "Session created successfully";
      showSuccessModal.value = true;
    }
  } catch (error) {
    errorMessage.value = error.message;
    showModal.value = true;
  }
};

</script>

<template>
  <div>
    <SessionCreationForm @create-session="handleSessionCreation" />
    <ErrorModal :show="showModal" :message="errorMessage" @update:show="showModal = $event" />
    <SuccessModal :show="showSuccessModal" :message="message" @update:show="showSuccessModal = $event" />
  </div>
</template>