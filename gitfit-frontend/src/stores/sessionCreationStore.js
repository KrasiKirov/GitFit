
import { defineStore } from 'pinia';
import { fetchSessions, createSession } from '../api.js';

export const useSessionStore = defineStore({
  id: 'sessions',
  state: () => ({
    sessions: [],
  }),
  actions: {
    async fetchSessions() {
      try {
        const response = await fetchSessions();
        this.sessions = response.data;
      } catch (error) {
        console.error(error);
      }
    },
    async createSession(sessionData) {
      try {
        await createSession(sessionData);
        this.fetchSessions();
      } catch (error) {
        console.error("Error creating session:", error);
        throw error; // Rethrow or handle as needed
      }
    }
  },
});
