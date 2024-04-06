<script setup>
import FitnessClassManagementCard from '@/components/FitnessClassManagementCard.vue';
import { ref, onMounted, computed } from 'vue';
import { useStore } from '@/stores/store';

const store = useStore();

onMounted(async () => {
    await store.fetchAndSetFitnessClasses();
});

const fitnessClasses = computed(() => store.fitnessClasses);
</script>

<template>
    <div class="grid grid-cols-3 gap-4">
        <FitnessClassManagementCard v-for="fclass in fitnessClasses" :key="fclass.name" :fitnessClass="fclass" />
    </div>
</template>