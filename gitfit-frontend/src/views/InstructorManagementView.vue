<script setup>
import InstructorCard from '@/components/InstructorCard.vue';
import OwnerNavbar from '@/components/OwnerNavbar.vue';
import { ref, onMounted, computed } from 'vue';
import { useInstructorStore } from '@/stores/instructorStore'

const instructorsTest = ref([
    {
        username: 'test1',
        firstName: 'John',
        lastName: 'Doe',
        email: 'john.Doe@gmail.com',
        password: 'john123',
    },
    {
        username: 'test1',
        firstName: 'John',
        lastName: 'Doe',
        email: 'john.Doe@gmail.com',
        password: 'john123',
    },
    {
        username: 'test1',
        firstName: 'John',
        lastName: 'Doe',
        email: 'john.Doe@gmail.com',
        password: 'john123',
    },
    {
        username: 'test1',
        firstName: 'John',
        lastName: 'Doe',
        email: 'john.Doe@gmail.com',
        password: 'john123',
    },
    {
        username: 'test1',
        firstName: 'John',
        lastName: 'Doe',
        email: 'john.Doe@gmail.com',
        password: 'john123',
    },
    {
        username: 'test1',
        firstName: 'John',
        lastName: 'Doe',
        email: 'john.Doe@gmail.com',
        password: 'john123',
    },
    {
        username: 'test1',
        firstName: 'John',
        lastName: 'Doe',
        email: 'john.Doe@gmail.com',
        password: 'john123',
    },
    {
        username: 'test1',
        firstName: 'John',
        lastName: 'Doe',
        email: 'john.Doe@gmail.com',
        password: 'john123',
    }
]);

const store = useInstructorStore();

onMounted(() => {
    store.fetchInstructors();
});

const instructors = computed(() => store.instructors);
</script>

<template>

    <div class="container mx-auto p-4">
      <div class="grid grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-4">
        <InstructorCard
          v-for="instructor in instructors"
          :key="instructor.username"
          :instructor="instructor"
        />
      </div>
    </div>
</template>