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
    <h1 class="text-3xl font-semibold text-center my-8">Good morning</h1>
    <div class="grid grid-cols-3 gap-4">
        <FitnessClassCard v-for="fclass in fitnessClasses" :key="fclass.name" :fitnessClass="fclass"
            @update="refreshClasses" />
    </div>
</template>