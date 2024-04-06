<template>
  <div class="flex flex-col bg-white rounded-xl shadow-md overflow-hidden md:max-w-2xl m-3 relative"
    @click.stop="toggleDropdown">
    <button class="absolute top-0 right-0 m-2 text-xl font-semibold" @click.stop="deleteCourseHandler">X</button>
    <div class="p-8">
      <div class="uppercase tracking-wide text-sm text-indigo-500 font-semibold">{{ fitnessClass.name }}</div>
      <p class="block mt-1 text-lg leading-tight font-medium text-black">{{ fitnessClass.description }}</p>
      <p class="mt-2 text-gray-500">Status: {{ fitnessClass.status }}</p>
    </div>
  </div>
</template>

<script setup>
import { defineProps } from 'vue';
import { useStore } from '@/stores/fitnessClassStore';

const props = defineProps({
  fitnessClass: {
    type: Object,
    required: true,
  },
});

const deleteCourseHandler = async () => {
  const store = useStore();
  await store.deleteFitnessClass(props.fitnessClass.name);
};
</script>