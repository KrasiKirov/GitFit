<script setup>
import VueDatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css';
import { ref, onMounted } from 'vue';

const date = ref();
const format = 'yyyy-MM-dd';

onMounted(() => {
    const startDate = new Date();
    const endDate = new Date();
    date.value = [startDate, endDate];
})

</script>

<template>
  <VueDatePicker v-model="date" range :format="format" />
</template>