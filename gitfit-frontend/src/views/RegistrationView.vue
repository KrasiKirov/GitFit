<script setup>
import RegistrationCard from '@/components/RegistrationCard.vue';
import { ref, onMounted, computed } from 'vue';
import { useRegistrationStore } from '@/stores/registrationStore';

const store = useRegistrationStore();

onMounted(() => {
    store.fetchRegistrationsByCustomerUsername("john_smith");
});

const registrations = computed(() => store.registrations);
console.log(registrations);
</script>

<template>

    <div class="container mx-auto p-4">
        <div class="grid grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-4">
            <RegistrationCard v-for="registration in registrations" :key="registration.id"
                :registration="registration" />
        </div>
    </div>
</template>