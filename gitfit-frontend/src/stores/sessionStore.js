import { defineStore } from 'pinia';
import { fetchSessions, fetchFilteredSessions, fetchSessionById } from '../api.js';

export const useSessionStore = defineStore({
    id: 'session',
    state: () => ({
        allSessions: [],
        filteredSessions: [],
        session: null
    }),
    actions: {
        async fetchAndSetSessions() {
            try {
                const response = await fetchSessions();
                this.allSessions = response.data;
            } catch (error) {
                console.error(error);
            }
        },
        async fetchAndSetFilteredSessions(filter) {
            try {
                const response = await fetchFilteredSessions(filter);
                this.filteredSessions = response.data;
            } catch (error) {
                console.error(error);
            }
        },
        async fetchAndSetSessionById(id) {
            try {
                const response = await fetchSessionById(id);
                this.session = response.data;
            } catch (error) {
                console.error(error);
            }
        }
    },
});