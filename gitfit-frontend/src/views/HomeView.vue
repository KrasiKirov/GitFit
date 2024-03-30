<script setup>
import FitnessClassCard from '@/components/FitnessClassCard.vue';
import { ref, onMounted, computed } from 'vue';
import { useStore } from '@/stores/store';

const fitnessClassesTest = ref([
    {
        name: 'Yoga',
        description: 'A class that focuses on flexibility and breathing exercises.',
        status: 'active'
    },
    {
        name: 'Zumba',
        description: 'A class that focuses on dance and aerobic exercises.',
        status: 'active'
    },
    {
        name: 'Pilates',
        description: 'A class that focuses on core strength and flexibility.',
        status: 'active'
    },
    {
        name: 'Kickboxing',
        description: 'A class that combines martial arts and cardio exercises.',
        status: 'active'
    },
    {
        name: 'Spin',
        description: 'A high-intensity cycling class.',
        status: 'active'
    },
    {
        name: 'Barre',
        description: 'A class that combines ballet-inspired moves with elements of Pilates, dance, and yoga.',
        status: 'active'
    },
    {
        name: 'HIIT',
        description: 'A high-intensity interval training class.',
        status: 'active'
    },
    {
        name: 'Piloxing',
        description: 'A class that combines Pilates, boxing, and dance.',
        status: 'active'
    },
    {
        name: 'Aerial Yoga',
        description: 'A class that combines traditional yoga poses with aerial arts.',
        status: 'active'
    },
    {
        name: 'Bootcamp',
        description: 'A class that combines strength training and cardiovascular exercises.',
        status: 'active'
    },
    {
        name: 'Pound',
        description: 'A class that combines cardio, conditioning, and strength training with drumming.',
        status: 'active'
    },
    {
        name: 'TRX',
        description: 'A class that uses suspension straps for a full-body workout.',
        status: 'active'
    }
]);

const store = useStore();

onMounted(async () => {
    await store.fetchAndSetFitnessClasses();
});

const fitnessClasses = computed(() => store.fitnessClasses);
</script>

<template>
    <h1 class="text-3xl font-semibold text-center my-8">Fitness Classes</h1>
    <div class="grid grid-cols-3 gap-4">
        <FitnessClassCard v-for="fclass in fitnessClassesTest" :key="fclass.name" :fitnessClass="fclass" />
    </div>
</template>