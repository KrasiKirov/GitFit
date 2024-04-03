import {defineStore} from 'pinia';
import { createBilling } from '@/api';

export const useBillingStore = defineStore({
    id: 'billing',
    state: () => ({
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
    },
});