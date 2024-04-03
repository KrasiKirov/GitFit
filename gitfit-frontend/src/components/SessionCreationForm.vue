<script setup>
import { ref, defineEmits } from 'vue';

const emit = defineEmits(['create-session']);

// const { uniqueSession } = defineProps({
//   uniqueSession: {
//     type: Object,
//     required: true,
//   },
// });



const formData = ref({
  price: 150,
  startTime: '14:00:00',
  endTime: '15:00:00',
  date: '2024-04-04',
  instructor: 'adam_smith',
  fitnessClass: 'Pilates',
});

const handleSubmit = () => {  
  const sessionData = {
    price: formData.value.price,
    startTime: formData.value.startTime,
    endTime: formData.value.endTime,
    date: formData.value.date,
    instructorUsername: formData.value.instructor,
    fitnessClassName: formData.value.fitnessClass
  };

  // Emit the form data to the parent component
  emit('create-session', sessionData);
};

</script>

<template>
  <div class="flex min-h-full flex-1 flex-col justify-center px-6 py-12 lg:px-8">
    <div class="sm:mx-auto sm:w-full sm:max-w-md">
      <h2 class="mt-32 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Create new session</h2>
    </div>

    <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-md">
      <form @submit.prevent="handleSubmit" class="space-y-6" action="#" method="POST">
        <div>
          <label for="createSessionPrice" class="block text-sm font-medium leading-6 text-gray-900">Price</label>
          <input v-model.number="formData.price" id="createSessionPrice" name="createSessionPrice" type="number" autocomplete="off" required class="mt-2 p-2 block w-full rounded-md border-gray-300 shadow-sm focus:border-custom-blue focus:ring focus:ring-custom-blue focus:ring-opacity-50" />
        </div>

        <div>
          <label for="createSessionStartTime" class="block text-sm font-medium leading-6 text-gray-900">Start Time</label>
          <input v-model="formData.startTime" id="createSessionStartTime" name="createSessionStartTime" type="text" required class="mt-2 p-2 block w-full rounded-md border-gray-300 shadow-sm focus:border-custom-blue focus:ring focus:ring-custom-blue focus:ring-opacity-50" />
        </div>

        <div>
          <label for="createSessionEndTime" class="block text-sm font-medium leading-6 text-gray-900">End Time</label>
          <input v-model="formData.endTime" id="createSessionEndTime" name="createSessionEndTime" type="text" required class="mt-2 p-2 block w-full rounded-md border-gray-300 shadow-sm focus:border-custom-blue focus:ring focus:ring-custom-blue focus:ring-opacity-50" />
        </div>

        <div>
          <label for="createSessionDate" class="block text-sm font-medium leading-6 text-gray-900">Date</label>
          <input v-model="formData.date" id="createSessionDate" name="createSessionDate" type="text" required class="mt-2 p-2 block w-full rounded-md border-gray-300 shadow-sm focus:border-custom-blue focus:ring focus:ring-custom-blue focus:ring-opacity-50" />
        </div>

        <div>
          <label for="createSessionInstructor" class="block text-sm font-medium leading-6 text-gray-900">Instructor</label>
          <input v-model="formData.instructor" id="createSessionInstructor" name="createSessionInstructor" type="text" required class="mt-2 p-2 block w-full rounded-md border-gray-300 shadow-sm focus:border-custom-blue focus:ring focus:ring-custom-blue focus:ring-opacity-50" />
        </div>

        <div>
          <label for="createSessionFitnessClass" class="block text-sm font-medium leading-6 text-gray-900">Fitness Class</label>
          <input v-model="formData.fitnessClass" id="createSessionFitnessClass" name="createSessionFitnessClass" type="text" required class="mt-2 p-2 block w-full rounded-md border-gray-300 shadow-sm focus:border-custom-blue focus:ring focus:ring-custom-blue focus:ring-opacity-50" />
        </div>

        <div>
          <button type="submit" class="mt-10 flex w-full justify-center rounded-md bg-custom-blue py-2 text-sm font-semibold leading-6 text-white shadow-sm">Create Session</button>
        </div>
      </form>
    </div>
  </div>
</template>
