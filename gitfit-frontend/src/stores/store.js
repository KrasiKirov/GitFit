import { defineStore } from 'pinia';
import { fetchPendingFitnessClasses } from '../api.js';

export const useStore = defineStore({
    id: 'main',
    state: () => ({
        fitnessClasses: [],
        approvedFitnessCLasses: [],
        pendingFitnessClasses: [],
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
        async fetchAndSetPendingFitnessClasses() {
            try {
                const response = await fetchPendingFitnessClasses();
                this.pendingFitnessClasses = response.data;
            } catch (error) {
                console.error(error);
            }
        }
    },
});