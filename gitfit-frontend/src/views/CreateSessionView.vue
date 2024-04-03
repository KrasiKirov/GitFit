<script setup>
import SessionCreationForm from '@/components/SessionCreationForm.vue';
import { ref, onMounted, computed } from 'vue';
import { useSessionStore } from '@/stores/sessionCreationStore';

const store = useSessionStore();

const SessionData = ref({
  startTime: '',
  endTime: '',
  date: '',
  instructor: 'TestInstructor',
  fitnessClass: 'TestFitnessClass'
});

onMounted(() => {
  store.fetchSessions();
})

const sessions = computed(() => store.sessions);

</script>

<template>
    <SessionCreationForm :uniqueSession="SessionData"/>
  </template>