<script setup>
import SessionCard from '@/components/SessionCard.vue';
import { computed } from 'vue';
import { useSessionStore } from '@/stores/sessionStore';

const store = useSessionStore();

const session = computed(() => store.session);
</script>

<template>
    <SessionCard :session="session">
    </SessionCard>
</template>