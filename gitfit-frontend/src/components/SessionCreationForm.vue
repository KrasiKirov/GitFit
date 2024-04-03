<script setup>
import { ref, defineEmits } from 'vue';

const emits = defineEmits(['create-session']);

// const { uniqueSession } = defineProps({
//   uniqueSession: {
//     type: Object,
//     required: true,
//   },
// });

const formData = ref({
  price: 150,
  startTime: '14:00:00',
  endTime: '15:00:00',
  date: '2024-04-04',
  instructor: 'adam_smith',
  fitnessClass: 'Pilates',
});

const handleSubmit = () => {
  emits('create-session', formData.value);
};

</script>

<template>
  <form @submit.prevent="handleSubmit">
    <!-- Form inputs bound with v-model to formData properties -->
    <input v-model="formData.price" type="number" placeholder="Price" />
    <input v-model="formData.startTime" type="time" placeholder="Start Time" />
    <input v-model="formData.endTime" type="time" placeholder="End Time" />
    <input v-model="formData.date" type="date" placeholder="Date" />
    <input v-model="formData.instructorUsername" type="text" placeholder="Instructor Username" />
    <input v-model="formData.fitnessClassName" type="text" placeholder="Fitness Class Name" />
    <button type="submit">Create Session</button>
  </form>
</template>