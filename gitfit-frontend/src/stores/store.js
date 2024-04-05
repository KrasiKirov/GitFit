import { defineStore } from 'pinia';
import { fetchFitnessClasses, deleteFitnessClass } from '../api.js'; // Update the import route to api.js

export const useStore = defineStore({
    id: 'main',
    state: () => ({
        fitnessClasses: [],
    }),
    actions: {
        async fetchAndSetFitnessClasses() {
            try {
                const response = await fetchFitnessClasses();
                this.fitnessClasses = response.data;
            } catch (error) {
                console.error(error);
            }
        },
        async deleteFitnessClass(name) {
            try {
                await deleteFitnessClass(name);
                this.fetchAndSetFitnessClasses();
            } catch (error) {
                console.error(error);
            }
        },
    },
});