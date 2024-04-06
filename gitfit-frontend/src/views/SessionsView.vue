<script setup>
import { ref, computed, onMounted, watch } from 'vue';
import IconToggleSort from '@/components/icons/IconToggleSort.vue';
import DatePicker from '@/components/DatePicker.vue';
import TimePicker from '@/components/TimePicker.vue';
import { useSessionStore } from '@/stores/sessionStore';
import { useInstructorStore } from '@/stores/instructorStore';
import { useStore } from '@/stores/store';

// Mock data for sessions
// const sessionsTest = ref([
//     {id: 1, date: '2024-04-01', startTime: '10:00 AM', endTime: '11:00 AM', price: '$10', instructor: 'John Doe', fclass: 'Yoga'},
//     {id: 2, date: '2024-04-02', startTime: '11:00 AM', endTime: '12:00 PM', price: '$15', instructor: 'Jane Smith', fclass: 'Zumba'},
//     {id: 3, date: '2024-04-03', startTime: '12:00 PM', endTime: '1:00 PM', price: '$20', instructor: 'John Doe', fclass: 'Pilates'},
//     {id: 4, date: '2024-04-04', startTime: '1:00 PM', endTime: '2:00 PM', price: '$25', instructor: 'Jane Smith', fclass: 'Kickboxing'},
//     {id: 5, date: '2024-04-05', startTime: '2:00 PM', endTime: '3:00 PM', price: '$30', instructor: 'John Doe', fclass: 'Spin'},
//     {id: 6, date: '2024-04-06', startTime: '3:00 PM', endTime: '4:00 PM', price: '$35', instructor: 'Jane Smith', fclass: 'Barre'},
//     {id: 7, date: '2024-04-07', startTime: '4:00 PM', endTime: '5:00 PM', price: '$40', instructor: 'John Doe', fclass: 'HIIT'},
//     {id: 8, date: '2024-04-08', startTime: '5:00 PM', endTime: '6:00 PM', price: '$45', instructor: 'Jane Smith', fclass: 'Piloxing'},
//     {id: 9, date: '2024-04-09', startTime: '6:00 PM', endTime: '7:00 PM', price: '$50', instructor: 'John Doe', fclass: 'Aerial Yoga'},
//     {id: 10, date: '2024-04-10', startTime: '7:00 PM', endTime: '8:00 PM', price: '$55', instructor: 'Jane Smith', fclass: 'Bootcamp'},
//     {id: 11, date: '2024-04-11', startTime: '8:00 PM', endTime: '9:00 PM', price: '$60', instructor: 'John Doe', fclass: 'Pound'}
// ]);

var filters = ref();
var selectedInstructor = ref('');
var selectedFitnessClass = ref('');
var selectedPrice = ref('');
var selectedStartDate = ref('');
var selectedEndDate = ref('');
var selectedStartTime = ref('');
var selectedEndTime = ref('');

const sortAttribute = ref('id');
const sortDirection = ref('asc');

const sessionStore = useSessionStore();
const instructorStore = useInstructorStore();
const store = useStore();

onMounted(async () => {
    await sessionStore.fetchAndSetSessions();
    await instructorStore.fetchInstructors();
    await store.fetchAndSetFitnessClasses();
});

var sessions = computed(() => sessionStore.sessions);
var instructors = computed(() => instructorStore.instructors);
var fitnessClasses = computed(() => store.fitnessClasses);

watch([selectedInstructor, selectedFitnessClass, selectedPrice, selectedStartDate, selectedEndDate, selectedStartTime, selectedEndTime],
async ([newInstructor, newFClass, newPrice, newStartDate, newEndDate, newStartTime, newEndTime]) => {
    filters.value = {
        'instructorUsername': newInstructor,
        'fitnessClassName': newFClass,
        'maxPrice': newPrice,
        'startDate': newStartDate,
        'endDate': newEndDate,
        'startTime': newStartTime,
        'endTime': newEndTime
    };
    console.log(filters.value);
    var params = '';
    if (newInstructor !== '') {
        params += `instructorUsername=${newInstructor}&`;
    }
    if (newFClass !== '') {
        params += `fitnessClassName=${newFClass}&`;
    }
    if (newPrice !== '') {
        params += `maxPrice=${newPrice}&`;
    }
    if (newStartDate !== '') {
        params += `startDate=${newStartDate}&`;
    }
    if (newEndDate !== '') {
        params += `endDate=${newEndDate}&`;
    }
    if (newStartTime !== '') {
        params += `startTime=${newStartTime}&`;
    }
    if (newEndTime !== '') {
        params += `endTime=${newEndTime}&`;
    }
    await sessionStore.fetchAndSetFilteredSessions(params);
    sessions = computed(() => sessionStore.sessions);
});

// const filteredSessions = computed(() => {
//   if (!selectedFitnessClass.value) return sessionsTest.value;
//   return sessionsTest.value.filter(session => session.instructor.includes(filter.value));
// });

const sortAttributes = ref([
    { value: 'id', label: 'ID' },
    { value: 'date', label: 'Date' },
    { value: 'startTime', label: 'Start Time' },
    { value: 'endTime', label: 'End Time' },
    { value: 'price', label: 'Price' },
    { value: 'instructorUsername', label: 'Instructor' },
    { value: 'fitnessClassName', label: 'Fitness Class' }
])

const sortedAndFilteredSessions = computed(() => {
    let sortedSessions = [...sessions.value];
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

const updateDates = (dates) => {
    selectedStartDate.value = dates[0].toISOString().slice(0, 10)
    selectedEndDate.value = dates[1].toISOString().slice(0, 10)
}

const updateStartTime = (time) => {
    selectedStartTime.value = time
}

const updateEndTime = (time) => {
    selectedEndTime.value = time
}

const updatePrice = (event) => {
    selectedPrice.value = event.target.value
}

</script>

<template>
    <div class="relative overflow-x-auto shadow-md">
        <DatePicker @update-dates="updateDates" />
        <TimePicker @update-time="updateStartTime" />
        <TimePicker @update-time="updateEndTime" />
        <select v-model="selectedFitnessClass">
          <option disabled value="">Filter by Fitness Class</option>
          <option value="">No Filter</option>
          <option v-for="fitnessClass in fitnessClasses" :key="fitnessClass.name">{{ fitnessClass.name }}</option>
        </select>
        <select v-model="selectedInstructor" class="mr-8">
          <option disabled value="">Filter by Instructor</option>
          <option value="">No Filter</option>
          <option v-for="instructor in instructors" :key="instructor.username">{{ instructor.username }}</option>
        </select>
            <input type="text" @keyup.enter="updatePrice" placeholder="Max Price" />
        <div class="text-s text-gray-700 bg-spindle dark:bg-gray-700 dark:text-gray-400">
            <label for="sortAttribute" class="sort-label">Sort by:</label>
            <select class="sort-dropdown" id="sortAttribute" v-model="sortAttribute">
                <option v-for="attribute in sortAttributes" :key="attribute.value" :value="attribute.value">
                    {{ attribute.label }}
                </option>
            </select>
            <button @click="toggleSortDirection" type="button" class="px-2 py-2 text-persianblue
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
          </tr>
        </thead>
        <tbody>
          <tr class="bg-linkwater border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
          v-for="session in sortedAndFilteredSessions" :key="session.id"
          @click="sessionStore.fetchAndSetSessionById(session.id); $router.push(`/sessions/${session.id}`)"
          style="cursor: pointer;"
          >
            <td class="px-6 py-4">{{ session.id }}</td>
            <td class="px-6 py-4">{{ session.date }}</td>
            <td class="px-6 py-4">{{ session.startTime }}</td>
            <td class="px-6 py-4">{{ session.endTime }}</td>
            <td class="px-6 py-4">{{ session.price }}</td>
            <td class="px-6 py-4">{{ session.instructorUsername }}</td>
            <td class="px-6 py-4">{{ session.fitnessClassName }}</td>
          </tr>
        </tbody>
    </table>
    </div>
</template>

<style scoped>
.sort-label {
  margin-left: 1rem;
  margin-right: 0.5rem;
  margin-bottom: 20px;
}
.sort-dropdown {
  margin-right: 1rem;
}
</style>