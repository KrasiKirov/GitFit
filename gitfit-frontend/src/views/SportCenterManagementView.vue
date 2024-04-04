<template>
  <div class="max-w-7xl mx-auto my-10 p-10 bg-white shadow-xl rounded-xl">
    
    <!-- Header Section with Title -->
    <div class="mb-10">
      <h1 class="text-4xl font-bold text-center text-blue-700">Sport Center Info</h1>
    </div>
    
    <!-- Main Content Section with Image and Form Side by Side -->
    <div class="grid grid-cols-1 lg:grid-cols-3 gap-10">
      
      <!-- Image Column spanning 1 of 3 columns on larger screens -->
      <div class="lg:col-span-1">
        <img src="../assets/SportCenterImg.jpg" alt="Sport Center" class="rounded-xl shadow-lg"/>
      </div>
      
      <div class="lg:col-span-2">
        <!-- Iterating over editable fields -->
        <div v-for="(value, key) in editableFields" :key="key" class="mb-8">
          <label class="block text-lg font-semibold text-gray-800 mb-2">
            {{ formatFieldName(key) }}
          </label>
          <template v-if="editModes[key]">
        <div v-if="key === 'operatingHours'" class="flex gap-4">
          <input v-model="operatingHours.openingTime" type="time"
            class="flex-1 px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
            placeholder="Opening Time" />
          <input v-model="operatingHours.closingTime" type="time"
            class="flex-1 px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
            placeholder="Closing Time" />
        </div>
        <div v-else>
          <input v-model="editableFields[key]"
            class="w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500" />
        </div>
        <div class="mt-3 flex justify-end gap-3">
          <button @click="cancelEdit(key)"
            class="px-4 py-2 rounded-lg border border-gray-300 text-gray-600 hover:bg-gray-100 focus:outline-none">
            Cancel
          </button>
          <button @click="saveChanges(key)"
            class="px-4 py-2 rounded-lg bg-blue-500 text-white hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-opacity-50">
            Save
          </button>
        </div>
      </template>
      <template v-else>
        <div class="flex justify-between items-center">
          <span class="text-gray-700">{{ displayValue(key, value) }}</span>
          <button @click="startEdit(key)"
            class="px-4 py-2 rounded-lg text-sm bg-blue-500 text-white hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-opacity-50">
            Edit
          </button>
        </div>
      </template>
    </div>
  </div>
</div>
</div>
</template>

<script setup>
import { ref, reactive, toRefs } from 'vue';
import { useSportCenterStore } from '@/stores/sportCenterStore';

const sportCenterStore = useSportCenterStore();
const sportCenterDetails = ref({});
const editModes = reactive({
  name: false,
  maxCapacity: false,
  operatingHours: false,
});

const operatingHours = reactive({
  openingTime: '',
  closingTime: '',
});

// Convert sport center details to editable fields
const editableFields = reactive({
  name: '',
  maxCapacity: '',
  operatingHours: '',
});

// Fetch sport center details on component mount and fill editableFields
sportCenterStore.fetchSportCenterDetails().then(() => {
  sportCenterDetails.value = sportCenterStore.sportCenter;
  editableFields.name = removeQuotes(sportCenterDetails.value.name);
  editableFields.maxCapacity = sportCenterDetails.value.maxCapacity;
  operatingHours.openingTime = sportCenterDetails.value.openingTime;
  operatingHours.closingTime = sportCenterDetails.value.closingTime;
  editableFields.operatingHours = `${operatingHours.openingTime} - ${operatingHours.closingTime}`;
});

const displayValue = (key, value) => {
  if (key === 'operatingHours') {
    return `${operatingHours.openingTime} - ${operatingHours.closingTime}`;
  }
  return value;
};

const removeQuotes = (str) => {
  if (typeof str === 'string') {
    // Remove escaped quotes and leading/trailing whitespace
    return str.replace(/^"|"$/g, '').trim();
  }
  return str;
};

function formatFieldName(key) {
  return key
    // Split based on uppercase letters
    .split(/(?=[A-Z])/)
    // Convert array back to string with spaces and capitalize first letter
    .join(' ')
    .replace(/^./, (str) => str.toUpperCase());
}

const startEdit = (key) => {
  editModes[key] = true;
};

const cancelEdit = (key) => {
  if (key === 'operatingHours') {
    // Reset to original values if cancel is clicked
    operatingHours.openingTime = sportCenterDetails.value.openingTime;
    operatingHours.closingTime = sportCenterDetails.value.closingTime;
  }
  editModes[key] = false;
};

// Method to save changes for each field
const saveChanges = async (key) => {
  switch (key) {
    case 'name':
      await sportCenterStore.updateName(editableFields.name);
      break;
    case 'maxCapacity':
      await sportCenterStore.updateMaxCapacity(editableFields.maxCapacity);
      break;
    case 'operatingHours':
      await sportCenterStore.updateOperatingHours(operatingHours.openingTime, operatingHours.closingTime);
      editableFields.operatingHours = `${operatingHours.openingTime} - ${operatingHours.closingTime}`;
      break;
  }
  editModes[key] = false;
};

</script>
