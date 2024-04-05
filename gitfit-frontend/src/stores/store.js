import { defineStore } from 'pinia';
import { fetchFitnessClasses, fetchApprovedFitnessClasses } from '../api.js'; // Update the import route to api.js

export const useStore = defineStore({
    id: 'main',
    state: () => ({
        fitnessClasses: [],
        approvedFitnessCLasses: [],
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
        async fetchAndSetApprovedFitnessClasses() {
            try {
                const response = await fetchApprovedFitnessClasses();
                this.approvedFitnessClasses = response.data;
            } catch (error) {
                console.error(error);
            }
        },
    },
});