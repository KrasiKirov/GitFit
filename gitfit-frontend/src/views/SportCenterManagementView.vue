<template>
    <div>
      <h1>Sport Center Details</h1>
      <form @submit.prevent="updateDetails">
        <div>
          <label for="name">Name:</label>
          <input v-model="sportCenter.name" type="text" id="name" />
        </div>
        <div>
          <label for="maxCapacity">Max Capacity:</label>
          <input v-model="sportCenter.maxCapacity" type="number" id="maxCapacity" />
        </div>
        <div>
          <label for="hours">Opening Hours:</label>
          <input v-model="sportCenter.hours" type="text" id="hours" />
        </div>
        <button type="submit">Update Details</button>
      </form>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import { fetchSportCenter, updateSportCenterName, updateSportCenterMaxCapacity, updateSportCenterHours } from '@/api';
  
  const sportCenter = ref({ name: '', maxCapacity: 0, hours: '' });
  
  const loadSportCenterDetails = async () => {
    const response = await fetchSportCenter();
    sportCenter.value = response.data;
  };
  
  const updateDetails = async () => {
    await updateSportCenterName(sportCenter.value.name);
    await updateSportCenterMaxCapacity(sportCenter.value.maxCapacity);
    await updateSportCenterHours(sportCenter.value.hours);
    // Optionally, reload the details to confirm the update
    await loadSportCenterDetails();
  };
  
  loadSportCenterDetails();
  </script>
  