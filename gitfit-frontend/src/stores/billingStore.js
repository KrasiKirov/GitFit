import {defineStore} from 'pinia';
import { createBilling } from '@/api';

export const useBillingStore = defineStore({
    id: 'billing',
    state: () => ({
        billing: JSON.parse(localStorage.getItem('billing')) || null,
    }),
    actions: {
        async createBilling(billing) {
            try {
                console.log('Creating billing', billing);
                const response = await createBilling(billing);
                localStorage.setItem('billing', JSON.stringify(response.data));
                this.updateBillingFromLocalStorage();
                return response;
            } catch (error) {
                console.log(error);
                return error.response;
            }
        },
        async getBilling(username) {
            try {
                const response = await getBilling(username);
                this.billing = response.data;
                return response;
            } catch (error) {
                console.log(error);
                return error.response;
            }
        },
        updateBillingFromLocalStorage() {
            this.billing = JSON.parse(localStorage.getItem('billing'))||null;
        },
    },
});