<script setup>
import { ref, defineEmits } from 'vue';

const emits = defineEmits(['create-fitnessClass']);

const formData = ref({
  name: '',
  description: '',
  approvalStatus: 'Pending',
});

const handleSubmit = () => {
  const fitnessClassData = {
    name : formData.value.name,
    description : formData.value.description,
    approvalStatus: 'PENDING',

  }
  emits('create-fitnessClass', fitnessClassData);
};

</script>

<template>
  <div class="flex flex-col items-center justify-center min-h-screen bg-linkwater py-12">
    <!-- Form container with a shadow and rounded corners -->
    <div class="w-full max-w-lg p-8 bg-white rounded-xl shadow-xl">
      <h2 class="text-4xl font-bold text-center text-persianblue mb-8 tracking-wide shadow-sm">
        Create New Fitness Class
      </h2>
      <form @submit.prevent="handleSubmit" class="space-y-6">
        <!-- Input for Name with a label -->
        <div>
          <label for="name" class="text-sm font-medium text-gray-700">Name</label>
          <input v-model="formData.name" type="text" id="name" placeholder="Enter name" class="mt-1 block w-full px-4 py-2 rounded-md border border-spindle focus:border-persianblue focus:ring focus:ring-persianblue focus:ring-opacity-50" />
        </div>
        <!-- Input for Description with a label -->
        <div>
          <label for="description" class="text-sm font-medium text-gray-700">Description</label>
          <input v-model="formData.description" type="text" id="description" placeholder="Enter description" class="mt-1 block w-full px-4 py-2 rounded-md border border-spindle focus:border-persianblue focus:ring focus:ring-persianblue focus:ring-opacity-50" />
        </div>
        <!-- Submit button -->
        <button type="submit" class="mt-4 px-4 py-2 bg-persianblue text-white font-semibold rounded-md w-full hover:bg-moodyblue focus:outline-none focus:ring-2 focus:ring-spindle focus:ring-offset-2 transition-colors">
          Propose Fitness Class
        </button>
      </form>
    </div>
  </div>
</template>