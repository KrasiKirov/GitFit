<script setup>
import FitnessClassCard from '@/components/FitnessClassCard.vue';
import { onMounted, computed } from 'vue';
import { useStore } from '@/stores/fitnessClassStore';

const store = useStore();

onMounted(async () => {
    await store.fetchAndSetPendingFitnessClasses(); 
});

const fitnessClasses = computed(() => store.pendingFitnessClasses);

const refreshClasses = async () => {
    await store.fetchAndSetPendingFitnessClasses();
}
</script>

<template>
    <div class="bg-gradient-to-r from-persianblue via-moodyblue to-spindle text-white text-center py-20">
        <h1 class="text-6xl font-bold">Good morning</h1>
    </div>
    <div class="grid grid-cols-3 gap-8 p-8">
        <FitnessClassCard v-for="fclass in fitnessClasses" :key="fclass.name" :fitnessClass="fclass"
            @update="refreshClasses"
            class="transform transition-transform duration-500 hover:scale-105 bg-linkwater rounded-lg shadow-md" />
    </div>
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap');

body {
    font-family: 'Inter', sans-serif;
}
</style>