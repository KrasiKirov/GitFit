import { defineStore } from 'pinia';
import { fetchInstructors, deleteInstructor } from '../api.js';

export const useInstructorStore = defineStore({
  id: 'instructors',
  state: () => ({
    instructors: [],
  }),
  actions: {
    async fetchInstructors() {
      try {
        const response = await fetchInstructors();
        this.instructors = response.data;
      } catch (error) {
        console.error(error);
      }
    },
    async deleteInstructor(username) {
      try {
        await deleteInstructor(username);
        this.fetchInstructors();
      } catch (error) {
        console.error(error);
      }
    },
  },
});
