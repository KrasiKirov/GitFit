import {defineStore} from 'pinia';
import { fetchOwner } from '@/api';


export const useOwnerStore = defineStore({
    id: 'owner',
    state: () => ({
        owner: JSON.parse(localStorage.getItem('owner')) || null,
    }),
    actions: {
        async fetchAndSetOwner() {
            try {
                const response = await fetchOwner();
                localStorage.setItem('owner', JSON.stringify(response.data));
                localStorage.setItem('userType', 'owner');
                this.updateOwnerFromLocalStorage();
            } catch (error) {
                console.error(error);
            }
        },
        updateInstructorFromLocalStorage() {
            this.instructor = JSON.parse(localStorage.getItem('instructor'))||null;
        },
    }
});