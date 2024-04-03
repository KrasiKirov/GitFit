
import { defineStore } from 'pinia';
import { fetchSessions, createSession } from '../api.js';

export const useSessionStore = defineStore({
  id: 'sessions',
  state: () => ({
    sessions: [],
  }),
  actions: {
    async createSession(sessionData) {
      try {
        const response = await createSession(sessionData);
        this.sessions.push(response.data);
        return response;
      } catch (error) {
        console.error("Error creating session:", error);
        throw error; // Rethrow or handle as needed
      }
    },
  },
});
