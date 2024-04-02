<script setup>
import { ref, computed, onMounted } from 'vue';
import IconToggleSort from '@/components/icons/IconToggleSort.vue';

// Mock data for sessions
const sessions = ref([
    {id: 1, date: '2024-04-01', startTime: '10:00 AM', endTime: '11:00 AM', price: '$10', instructor: 'John Doe', fclass: 'Yoga'},
    {id: 2, date: '2024-04-02', startTime: '11:00 AM', endTime: '12:00 PM', price: '$15', instructor: 'Jane Smith', fclass: 'Zumba'},
    {id: 3, date: '2024-04-03', startTime: '12:00 PM', endTime: '1:00 PM', price: '$20', instructor: 'John Doe', fclass: 'Pilates'},
    {id: 4, date: '2024-04-04', startTime: '1:00 PM', endTime: '2:00 PM', price: '$25', instructor: 'Jane Smith', fclass: 'Kickboxing'},
    {id: 5, date: '2024-04-05', startTime: '2:00 PM', endTime: '3:00 PM', price: '$30', instructor: 'John Doe', fclass: 'Spin'},
    {id: 6, date: '2024-04-06', startTime: '3:00 PM', endTime: '4:00 PM', price: '$35', instructor: 'Jane Smith', fclass: 'Barre'},
    {id: 7, date: '2024-04-07', startTime: '4:00 PM', endTime: '5:00 PM', price: '$40', instructor: 'John Doe', fclass: 'HIIT'},
    {id: 8, date: '2024-04-08', startTime: '5:00 PM', endTime: '6:00 PM', price: '$45', instructor: 'Jane Smith', fclass: 'Piloxing'},
    {id: 9, date: '2024-04-09', startTime: '6:00 PM', endTime: '7:00 PM', price: '$50', instructor: 'John Doe', fclass: 'Aerial Yoga'},
    {id: 10, date: '2024-04-10', startTime: '7:00 PM', endTime: '8:00 PM', price: '$55', instructor: 'Jane Smith', fclass: 'Bootcamp'},
    {id: 11, date: '2024-04-11', startTime: '8:00 PM', endTime: '9:00 PM', price: '$60', instructor: 'John Doe', fclass: 'Pound'}
]);

const filter = ref('');
const sortAttribute = ref('id');
const sortDirection = ref('asc');

const sortAttributes = ref([
    { value: 'id', label: 'ID' },
    { value: 'date', label: 'Date' },
    { value: 'startTime', label: 'Start Time' },
    { value: 'endTime', label: 'End Time' },
    { value: 'price', label: 'Price' },
    { value: 'instructor', label: 'Instructor' },
    { value: 'fclass', label: 'Fitness Class' }
])

const filteredSessions = computed(() => {
  if (!filter.value) return sessions.value;
  return sessions.value.filter(session => session.name.includes(filter.value));
});

const sortedAndFilteredSessions = computed(() => {
    let sortedSessions = [...filteredSessions.value];
    if (sortAttribute.value) {
        sortedSessions.sort((a, b) => {
            if (sortDirection.value === 'asc') {
                return a[sortAttribute.value] > b[sortAttribute.value] ? 1 : -1;
            } else {
                return a[sortAttribute.value] < b[sortAttribute.value] ? 1 : -1;
            }
        });
    }
    return sortedSessions;
});

const toggleSortDirection = () => {
    sortDirection.value = sortDirection.value === 'asc' ? 'desc' : 'asc';
}

</script>

<template>
    <div class="relative overflow-x-auto shadow-md">
      <!-- <input type="text" v-model="filter" placeholder="Filter sessions" /> -->
        <div class="text-s text-gray-700 uppercase bg-spindle dark:bg-gray-700 dark:text-gray-400">
            <label for="sortAttribute" class="sort-label">Sort by:</label>
            <select class="sort-dropdown" id="sortAttribute" v-model="sortAttribute">
                <option v-for="attribute in sortAttributes" :key="attribute.value" :value="attribute.value">
                    {{ attribute.label }}
                </option>
            </select>
            <button @click="toggleSortDirection" type="button" class="px-1 py-1 text-blue-700
            hover:bg-moodyblue hover:text-linkwater font-medium rounded-lg text-sm p-2.5 text-center 
            inline-flex items-center me-2 dark:border-blue-500 dark:text-blue-500 dark:hover:text-white 
            dark:focus:ring-blue-800 dark:hover:bg-blue-500">
                <IconToggleSort></IconToggleSort>
                <span class="sr-only">Icon description</span>
            </button>
        </div>
    <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
        <thead class="text-xs text-gray-700 uppercase bg-spindle dark:bg-gray-700 dark:text-gray-400">
          <tr>
            <th scope="col" class="px-6 py-3">ID</th>
            <th scope="col" class="px-6 py-3">Date</th>
            <th scope="col" class="px-6 py-3">Start Time</th>
            <th scope="col" class="px-6 py-3">End Time</th>
            <th scope="col" class="px-6 py-3">Price</th>
            <th scope="col" class="px-6 py-3">Instructor</th>
            <th scope="col" class="px-6 py-3">Fitness Class</th>
            <!-- Add more columns as needed -->
          </tr>
        </thead>
        <tbody>
          <tr class="bg-linkwater border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
          v-for="session in sortedAndFilteredSessions" :key="session.id">
            <td class="px-6 py-4">{{ session.id }}</td>
            <td class="px-6 py-4">{{ session.date }}</td>
            <td class="px-6 py-4">{{ session.startTime }}</td>
            <td class="px-6 py-4">{{ session.endTime }}</td>
            <td class="px-6 py-4">{{ session.price }}</td>
            <td class="px-6 py-4">{{ session.instructor }}</td>
            <td class="px-6 py-4">{{ session.fclass }}</td>
            <!-- Add more cells as needed -->
          </tr>
        </tbody>
    </table>
    </div>
</template>

<style scoped>
.sort-label {
  margin-left: 1rem;
  margin-right: 0.5rem;
}
.sort-dropdown {
  margin-right: 1rem;
}
</style>