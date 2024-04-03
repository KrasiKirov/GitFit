import {defineStore} from 'pinia';
import { createCustomer } from '@/api';

export const useCustomerStore = defineStore({
    id: 'customer',
    state: () => ({
        customer: JSON.parse(localStorage.getItem('customer')) || null,
    }),
    actions: {
        getCustomer() {
            return this.customer;
        },
        async createCustomer(customer) {
            try {
                console.log('Creating customer', customer);
                const response = await createCustomer(customer);
                console.log("no error in creating customer");
                localStorage.setItem('customer', JSON.stringify(response.data));
                this.updateCustomerFromLocalStorage();
                // this.customer = response.data;
                console.log(this.customer);
                return response;
            } catch (error) {
                console.log(error);
                console.log("error store");
                return error.response;
            }
        },
        updateCustomerFromLocalStorage() {
            this.customer = JSON.parse(localStorage.getItem('customer'))||null;
        },
    },
});