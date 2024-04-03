import { defineStore } from 'pinia';
import { fetchFitnessClasses, createCustomer } from '../api.js'; // Update the import route to api.js

export const useStore = defineStore({
    id: 'main',
    state: () => ({
        fitnessClasses: [],
        loggedInUser: null,
    }),
    actions: {
        async fetchAndSetFitnessClasses() {
            try {
                const response = await fetchFitnessClasses();
                console.log(response.data);
                this.fitnessClasses = response.data;
            } catch (error) {
                console.error(error);
            }
        },
        // async createCustomer(customer) {
        //     try {
        //         console.log('Creating customer', customer);
        //         const response = await createCustomer(customer);
        //         this.loggedInUser = response.data.username;
        //         console.log(this.response.username);
        //     } catch (error) {
        //         console.error(error);
        //     }
        // }
    },
});