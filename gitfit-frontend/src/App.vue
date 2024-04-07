<script setup>
import { RouterLink, RouterView } from 'vue-router'
import Navbar from '@/components/Navbar.vue';
import NavbarCustomer from '@/components/NavbarCustomer.vue';
</script>

<template>
    <div v-if="navbar === 'customer'">
        <NavbarCustomer />
    </div>
    <div v-else>
        <Navbar />
    </div>
    <RouterView />
</template>

<script>
import { emitter } from './main';

export default {
    inject: ['emitter'],
    data() {
        return {
            navbar: "customer",
        };
    },
    created() {
        this.emitter.$on('loginCustomer', this.loginCustomer);
        this.emitter.$on('loginInstructor', this.loginInstructor);
        this.emitter.$on('loginOwner', this.loginOwner);
    },
    methods: {
        loginCustomer() {
            this.navbar = "customer";
        },
        loginInstructor() {
            this.navbar = "instructor";
        },
        loginOwner() {
            this.navbar = "owner";
        }
    }
};

</script>

<style scoped></style>
