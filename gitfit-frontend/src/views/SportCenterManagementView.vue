<template>
  <div class="flex min-h-full flex-col justify-center items-center ">
    <div class="flex flex-col min-h-screen items-center">
      <div class="sm:mx-auto sm:w-full sm:max-w-md">
        <h2 class="mt-20 mb-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Sport Center Details</h2>
      </div>
      <div class="w-full bg-white overflow-hidden shadow rounded-lg border mb-10">
        <div class="border-t border-gray-200 px-4 py-5 sm:p-0">
          <dl class="sm:divide-y sm:divide-gray-200">
            <div class="py-3 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
              <dt class="text-sm font-medium text-gray-500">
                Name
              </dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                {{ sportCenter.name }}
              </dd>
            </div>
            <div class="py-3 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
              <dt class="text-sm font-medium text-gray-500">
                Max Capacity
              </dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                {{ sportCenter.maxCapacity }}
              </dd>
            </div>
            <div class="py-3 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
              <dt class="text-sm font-medium text-gray-500">
                Opening Hours
              </dt>
              <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                {{ sportCenter.openingHours }}
              </dd>
            </div>
          </dl>
          <div class="px-4 py-5 sm:px-6 flex justify-end">
            <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded" @click="editDetails">
              Edit Details
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { defineEmits } from 'vue';
import { useSportCenterStore } from '@/stores/sportCenterStore'; // Assume you have a store for sport center

const emit = defineEmits(['editDetails']);
const sportCenterStore = useSportCenterStore();
const sportCenter = ref(sportCenterStore.sportCenter);

const editDetails = () => {
  emit('editDetails', sportCenter.value);
};

// Optionally, if you need to fetch sport center details on component mount:
onMounted(() => {
  // Assuming sportCenterStore has a method to fetch and update local sportCenter data
  sportCenterStore.fetchSportCenterDetails();
});
</script>