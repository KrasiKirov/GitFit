<script setup>
import { defineProps } from "vue";
import { useSessionStore } from '@/stores/sessionCreationStore';

const { uniqueSession } = defineProps({
  uniqueSession: {
    type: Object,
    required: true,
  },
});

</script>

<template>
  <div class="flex min-h-full flex-1 flex-col justify-center px-6 py-12 lg:px-8">
    <div class="sm:mx-auto sm:w-full sm:max-w-md">
      <h2 class="mt-32 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Create new session</h2>
    </div>

    <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-md">
      <form class="space-y-6" action="#" method="POST">
        <div>
          <label for="createSessionPrice" class="block text-sm font-medium leading-6 text-gray-900">Price</label>
          <input id="createSessionPrice" name="createSessionPrice" type="number" autocomplete="off" required class="mt-2 p-2 block w-full rounded-md border-gray-300 shadow-sm focus:border-custom-blue focus:ring focus:ring-custom-blue focus:ring-opacity-50" />
        </div>

        <div>
          <label for="createSessionStartTime" class="block text-sm font-medium leading-6 text-gray-900">Start Time</label>
          <input id="createSessionStartTime" name="createSessionStartTime" type="time" required class="mt-2 p-2 block w-full rounded-md border-gray-300 shadow-sm focus:border-custom-blue focus:ring focus:ring-custom-blue focus:ring-opacity-50" />
        </div>

        <div>
          <label for="createSessionEndTime" class="block text-sm font-medium leading-6 text-gray-900">End Time</label>
          <input id="createSessionEndTime" name="createSessionEndTime" type="time" required class="mt-2 p-2 block w-full rounded-md border-gray-300 shadow-sm focus:border-custom-blue focus:ring focus:ring-custom-blue focus:ring-opacity-50" />
        </div>

        <div>
          <label for="createSessionDate" class="block text-sm font-medium leading-6 text-gray-900">Date</label>
          <input id="createSessionDate" name="createSessionDate" type="date" required class="mt-2 p-2 block w-full rounded-md border-gray-300 shadow-sm focus:border-custom-blue focus:ring focus:ring-custom-blue focus:ring-opacity-50" />
        </div>

        <div>
          <label for="createSessionInstructor" class="block text-sm font-medium leading-6 text-gray-900">Instructor</label>
          <input id="createSessionInstructor" name="createSessionInstructor" type="text" required class="mt-2 p-2 block w-full rounded-md border-gray-300 shadow-sm focus:border-custom-blue focus:ring focus:ring-custom-blue focus:ring-opacity-50" />
        </div>

        <div>
          <label for="createSessionFitnessClass" class="block text-sm font-medium leading-6 text-gray-900">Fitness Class</label>
          <input id="createSessionFitnessClass" name="createSessionFitnessClass" type="text" required class="mt-2 p-2 block w-full rounded-md border-gray-300 shadow-sm focus:border-custom-blue focus:ring focus:ring-custom-blue focus:ring-opacity-50" />
        </div>

        <div>
          <button type="submit" class="mt-10 flex w-full justify-center rounded-md bg-custom-blue py-2 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-custom-dark-blue focus:outline-none focus:ring-2 focus:ring-custom-blue focus:ring-offset-2">Create Session</button>
        </div>
      </form>
    </div>
  </div>
</template>
