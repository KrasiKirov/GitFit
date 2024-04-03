import {defineStore} from 'pinia';
import { createCustomer } from '@/api';

export const useCustomerStore = defineStore({
    id: 'customer',
    state: () => ({
        customer: null,
    }),
    actions: {
        async createCustomer(customer) {
            try {
                console.log('Creating customer', customer);
                const response = await createCustomer(customer);
                console.log("no error in creating customer");
                this.customer = response.data.username;
                console.log(this.customer);
                return response;
            } catch (error) {
                console.log(error);
                console.log("error store");
                return error.response;
            }
        }
    },
});