import { defineStore } from 'pinia';
import { fetchInstructors, deleteInstructor } from '../api.js';

export const useInstructorStore = defineStore({
  id: 'instructors',
  state: () => ({
    instructors: [],
    instructorLookup: {},
  }),
  actions: {
    async fetchInstructors() {
      try {
        const response = await fetchInstructors();
        this.instructors = response.data;
        this.instructors.forEach(instructor => {
          this.instructorLookup[instructor.username] = instructor;
        });
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
