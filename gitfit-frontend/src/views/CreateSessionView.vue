<script setup>
import { ref } from 'vue';
import ErrorModal from '@/components/ErrorModal.vue';
import SessionCreationForm from '@/components/SessionCreationForm.vue';
import { useSessionStore } from '@/stores/sessionCreationStore';


const sessionStore = useSessionStore();
const showModal = ref(false);
const errorMessage = ref('');

const handleSessionCreation = async (sessionData) => {
  try {
    const response = await sessionStore.createSession(sessionData);
    if (response && response.status === 200) {
      console.log("Session created successfully");
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
  </div>
</template>