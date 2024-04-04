import { defineStore } from 'pinia';
import { fetchSportCenter, updateSportCenterName, updateSportCenterMaxCapacity, updateSportCenterHours } from '../api.js';

export const useSportCenterStore = defineStore({
  id: 'sportCenter',
  state: () => ({
    sportCenter: [],
  }),
  actions: {
    async fetchSportCenterDetails() {
      try {
        const response = await fetchSportCenter();
        this.sportCenter = response.data;
      } catch (error) {
        console.error('Failed to fetch sport center details:', error);
      }
    },
    async updateName(name) {
      try {
        await updateSportCenterName(name);
        // Optionally refresh the sport center details after update
        await this.fetchSportCenterDetails();
      } catch (error) {
        console.error('Failed to update sport center name:', error);
      }
    },
    async updateMaxCapacity(maxCapacity) {
      try {
        await updateSportCenterMaxCapacity(maxCapacity);
        // Optionally refresh the sport center details after update
        await this.fetchSportCenterDetails();
      } catch (error) {
        console.error('Failed to update sport center max capacity:', error);
      }
    },
    async updateOperatingHours(openingTime, closingTime) {
      try {
        // Assuming updateSportCenterHours accepts an object with openingTime and closingTime
        await updateSportCenterHours({ openingTime, closingTime });
        // Optionally refresh the sport center details after update
        await this.fetchSportCenterDetails();
      } catch (error) {
        console.error('Failed to update sport center operating hours:', error);
      }
    },
  },
});