<script setup>
import FitnessClassCard from '@/components/FitnessClassCard.vue';
import { ref, onMounted, computed } from 'vue';
import { useStore } from '@/stores/store';

const store = useStore();

onMounted(async () => {
    await store.fetchAndSetApprovedFitnessClasses();
});

const fitnessClasses = computed(() => store.approvedFitnessClasses);
</script>

<template>
    <h1 class="text-3xl font-semibold text-center my-8">Good morning, ???</h1>
    <div class="grid grid-cols-3 gap-4">
        <FitnessClassCard v-for="fclass in fitnessClasses" :key="fclass.name" :fitnessClass="fclass" />
    </div>
</template>