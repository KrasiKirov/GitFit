<script setup>
import FitnessClassCreationForm from '@/components/FitnessClassCreationForm.vue';
import { useFitnessClassStore } from '@/stores/fitnessClassCreationStore';

const fitnessClassStore = useFitnessClassStore();

const handleFitnessClassCreation = async (fitnessClassData) => {
  try {
    await fitnessClassStore.createFitnessClass(fitnessClassData);
    // Optionally: handle successful session creation, such as clearing a form or showing a success message
  } catch (error) {
    // Optionally: handle error, such as showing an error message
    console.error('Failed to create fitness class:', error);
  }
};

</script>

<template>
  <div>
    <FitnessClassCreationForm @create-fitnessClass="handleFitnessClassCreation" />
  </div>
</template>