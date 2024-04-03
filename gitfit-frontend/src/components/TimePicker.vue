<script setup>
import VueDatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css';
import { ref, onMounted } from 'vue';

const format = 'HH:mm:ss';
const time = ref();
</script>

<template>
  <VueDatePicker v-model="time" time-picker range :format="format" />
</template>