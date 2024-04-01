<script setup>
import SessionCreationForm from '@/components/SessionCreationForm.vue';
import { ref, onMounted, computed } from 'vue';
import { useStore } from '@/stores/store.js';

const SessionData = ref({
  startTime: '',
  endTime: '',
  date: '',
  instructor: 'TestInstructor',
  fitnessClass: 'TestFitnessClass'
});

</script>

<template>
    <h1 class="text-3xl font-semibold text-center my-8">Create new Session</h1>
    <SessionCreationForm :uniqueSession="SessionData" />
  </template>