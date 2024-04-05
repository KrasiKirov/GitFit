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

export function updateCustomerPassword(password) {
    console.log("entereing api.js");
    return AXIOS.put(`/customer/password`, password);
    console.log("exiting api.js");
}

export function createBilling(billing) {
    return AXIOS.put(`/customers/${billing.username}/billing`, billing);
}

export function getBilling(username) {
    return AXIOS.get(`/customers/${username}/billing`);
}

export function deleteBilling(username) {
    console.log("entereing deleteBilling apijs");
    console.log(username);
    return AXIOS.delete(`/customers/${username}/billing`);
}

export function createInstructor(instructor) {
    console.log("entereing api.js");
    return AXIOS.post('/instructor', instructor);
}
// return AXIOS.post('/customer', {data: customer, headers: { 'Content-Type': 'application/json' }});


export function fetchInstructors() {
    return AXIOS.get('/instructors/');
}

export function deleteInstructor(username) {
    return AXIOS.delete('/instructor/', { data: { username: username }, headers: { 'Content-Type': 'application/json' }});
}
