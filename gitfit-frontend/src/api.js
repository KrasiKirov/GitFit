import axios from 'axios'

const frontendUrl = 'http://' + import.meta.env.VITE_APP_FRONTEND_HOST + ':' + import.meta.env.VITE_APP_FRONTEND_PORT;
const backendUrl = 'http://' + import.meta.env.VITE_APP_BACKEND_HOST + ':' + import.meta.env.VITE_APP_BACKEND_PORT + '/api';

const AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
});

export function fetchFitnessClasses() {
    return AXIOS.get('/fitnessclasses');
}

export function createCustomer(customer) {
    console.log("entereing api.js");
    return AXIOS.post('/customer', customer);
}

export function createBilling(billing) {
    return AXIOS.put(`/customers/${billing.username}/billing`, billing);
}

// return AXIOS.post('/customer', {data: customer, headers: { 'Content-Type': 'application/json' }});