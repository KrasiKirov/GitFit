
import { defineStore } from 'pinia';
import { fetchFitnessClasses, createFitnessClass } from '../api.js';

export const useFitnessClassStore = defineStore({
  id: 'fitnessClasses',
  state: () => ({
    fitnessClasses: [],
  }),
  actions: {
    async fetchFitnessClasses() {
      try {
        const response = await fetchFitnessClasses();
        this.fitnessClasses = response.data;
      } catch (error) {
        console.error(error);
      }
    },
    async createFitnessClass(fitnessClassData) {
      try {
        await createFitnessClass(fitnessClassData);
        this.fetchFitnessClasses();
      } catch (error) {
        console.error(error);
      }
    },
  },
});
