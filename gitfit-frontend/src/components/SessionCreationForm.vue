<script setup>
import { defineProps } from "vue";
import { createSession } from "@/api"

const { uniqueSession } = defineProps({
  uniqueSession: {
    type: Object,
    required: true,
  },
});

const submitForm = async () => {
  const sessionData = {
    startTime: uniqueSession.startTime,
    endTime: uniqueSession.endTime,
    date: uniqueSession.date,
    instructor: 'TestInstructor',
    fitnessClass: 'TestFitnessclass',
  };

  try {
    const response = await createSession(sessionData);
    console.log('Session created:', response.data);
    // Handle the success scenario
  } catch (error) {
    console.error(error.response);
    // Handle the error scenario
  }
};

</script>

<template>
  <div class="flex flex-col items-center justify-center p-6">
    <!-- Start Time Input -->
    <div class="mb-6 w-full">
      <label for="startDate" class="block mb-2 text-sm font-medium text-gray-700">Start Time</label>
      <input
        type="time"
        id="startDate"
        v-model="uniqueSession.startTime"
        class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"
        required
      />
    </div>
    <!-- End Time Input -->
    <div class="mb-6 w-full">
      <label for="endDate" class="block mb-2 text-sm font-medium text-gray-700">End Time</label>
      <input
        type="time"
        id="endDate"
        v-model="uniqueSession.endTime"
        class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"
        required
      />
    </div>
    <!-- Date Input -->
    <div class="mb-6 w-full">
      <label for="date" class="block mb-2 text-sm font-medium text-gray-700">Date</label>
      <input
        type="date"
        id="date"
        v-model="uniqueSession.date"
        class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"
        required
      />
    </div>
    <!-- Submit Button -->
    <button
      type="button"
      @click="submitForm"
      class="text-white bg-blue-500 hover:bg-blue-700 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center"
    >
      Create Session
    </button>
  </div>
</template>
