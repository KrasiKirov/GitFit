<script setup>
import { ref, defineEmits } from 'vue';

const emit = defineEmits(['create-fitnessClass']);

const formData = ref({
  name: '',
  description: ''
});

const handleSubmit = () => {  
  const fitnessClassData = {
    name: formData.value.name,
    description: formData.value.description
  };

  // Emit the form data to the parent component
  emit('create-fitnessClass', fitnessClassData);
};

</script>

<template>
  <div class="flex min-h-full flex-1 flex-col justify-center px-6 py-12 lg:px-8">
    <div class="sm:mx-auto sm:w-full sm:max-w-md">
      <h2 class="mt-32 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Create new fitness class</h2>
    </div>

    <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-md">
      <form @submit.prevent="handleSubmit" class="space-y-6" action="#" method="POST">
        <div>
          <label for="createFitnessClassName" class="block text-sm font-medium leading-6 text-gray-900">Name</label>
          <input v-model="formData.name" id="createFitnessClassName" name="createFitnessClassName" type="text" required class="mt-2 p-2 block w-full rounded-md border-gray-300 shadow-sm focus:border-custom-blue focus:ring focus:ring-custom-blue focus:ring-opacity-50" />
        </div>

        <div>
          <label for="createFitnessClassDescription" class="block text-sm font-medium leading-6 text-gray-900">Description</label>
          <input v-model="formData.description" id="createFitnessClassDescription" name="createFitnessClassDescription" type="text" required class="mt-2 p-2 block w-full rounded-md border-gray-300 shadow-sm focus:border-custom-blue focus:ring focus:ring-custom-blue focus:ring-opacity-50" />
        </div>

        <div>
          <button type="submit" class="mt-10 flex w-full justify-center rounded-md bg-custom-blue py-2 text-sm font-semibold leading-6 text-white shadow-sm">Create Session</button>
        </div>
      </form>
    </div>
  </div>
</template>
