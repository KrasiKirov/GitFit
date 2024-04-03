<script setup>
import SessionCreationForm from '@/components/SessionCreationForm.vue';
import { useSessionStore } from '@/stores/sessionCreationStore';


const sessionStore = useSessionStore();

const handleSessionCreation = async (sessionData) => {
  try {
    const response = await sessionStore.createSession(sessionData);
    if (response && response.status === 200) {
      console.log("Session created successfully");
    } else {
      // Handle unexpected response structure
      console.log("Unexpected response", response);
    }
  } catch (error) {
    // Handle error, such as showing an error message
    console.error('Failed to create session:', error);
  }
};

</script>

<template>
  <div>
    <SessionCreationForm @create-session="handleSessionCreation" />
  </div>
</template>