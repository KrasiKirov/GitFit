<script setup>
import { RouterLink, RouterView } from 'vue-router'
import Navbar from '@/components/Navbar.vue';
</script>

<template>

    <Navbar v-if="!$route.meta.hideNavbar" />
    <RouterView />
</template>

<style scoped></style>
