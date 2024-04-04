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
        await this.fetchSportCenterDetails();
      } catch (error) {
        console.error('Failed to update sport center name:', error);
      }
    },
    async updateMaxCapacity(maxCapacity) {
      try {
        await updateSportCenterMaxCapacity(maxCapacity);
        await this.fetchSportCenterDetails();
      } catch (error) {
        console.error('Failed to update sport center max capacity:', error);
      }
    },
    async updateOperatingHours(openingTime, closingTime) {
      try {
        // Pass openingTime and closingTime directly to updateSportCenterHours
        await updateSportCenterHours(openingTime, closingTime);
        await this.fetchSportCenterDetails();
      } catch (error) {
        console.error('Failed to update sport center operating hours:', error);
      }
    },
  },
});