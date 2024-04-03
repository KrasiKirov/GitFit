import {defineStore} from 'pinia';
import { createBilling } from '@/api';

export const useBillingStore = defineStore({
    id: 'billing',
    state: () => ({
        billing: null,
    }),
    actions: {
        async createBilling(billing) {
            try {
                console.log('Creating billing', billing);
                const response = await createBilling(billing);
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
        }
    },
});