import { defineStore } from 'pinia';
import { fetchRegistrations, fetchRegistrationsByCustomerUsername, deleteRegistation } from '../api.js';

export const useRegistrationStore = defineStore({
  id: 'registrations',
  state: () => ({
    registrations: [],
  }),
  actions: {
    async fetchRegistrations() {
      try {
        const response = await fetchRegistrations();
        this.registrations = response.data;
      } catch (error) {
        console.error(error);
      }
    },
    async fetchRegistrationsByCustomerUsername(username) {
      try {
        const response = await fetchRegistrationsByCustomerUsername(username);
        this.registrations = response.data;
      } catch (error) {
        console.error(error);
      }
    },
    async deleteRegistration(registrationId) {
      try {
        await deleteRegistation(registrationId);
      } catch (error) {
        console.error(error);
      }
    }
  },
});
