<script setup>
import { useRegistrationStore } from '@/stores/registrationStore';
import { defineProps } from 'vue';

const props = defineProps({
    registration: {
        type: Object,
        required: true
    }
});

const deleteRegistrationHandler = async () => {
    const store = useRegistrationStore();
    alert(props.registration.id);
    await store.deleteRegistration(props.registration.id);
    await store.fetchRegistrationsByCustomerUsername("testuser1");
};
</script>

<template>
    <div class="instructor-card flex flex-col bg-white rounded-xl shadow-md overflow-hidden md:max-w-2xl m-3 relative"
        @click.stop="toggleDropdown">
        <button class="absolute top-0 right-0 m-2 text-xl font-semibold"
            @click.stop="deleteRegistrationHandler">X</button>
        <div class="p-8">
            <div class="uppercase tracking-wide text-sm text-indigo-500 font-semibold">
                {{ registration.id }}
            </div>
            <!-- Additional details shown when expanded -->
            <p class="mt-2 text-gray-500">Date: {{ registration.date }}</p>
            <p class="mt-2 text-gray-500">SessionId: {{ registration.sessionId }}</p>
        </div>
    </div>
</template>



<style scoped></style>