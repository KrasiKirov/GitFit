import {defineStore} from 'pinia';
import { fetchOwner, updateOwnerPassword } from '@/api';


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
                localStorage.setItem('userType', 'Owner');
                this.updateOwnerFromLocalStorage();
            } catch (error) {
                console.error(error);
            }
        },
        async updateOwnerPassword(password) {
            try {
                console.log('Updating owner password');
                console.log(password);
                // console.log(password);
                const response = await updateOwnerPassword(password);
                console.log(response)
                localStorage.setItem('owner', JSON.stringify(response.data));
                this.updateOwnerFromLocalStorage();
                return response;
            } catch (error) {
                return error.response;
            }
        },
        updateOwnerFromLocalStorage() {
            this.instructor = JSON.parse(localStorage.getItem('owner'))||null;
        },
    }
});