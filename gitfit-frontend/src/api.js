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

export function fetchInstructors() {
    return AXIOS.get('/instructors/');
}

export function deleteInstructor(username) {
    return AXIOS.delete('/instructor/', { data: { username: username }, headers: { 'Content-Type': 'application/json' }});
}

export function fetchSessions() {
    return AXIOS.get('/sessions');
}

export function fetchFilteredSessions(filter) {
    return AXIOS.get('/sessions/filter?' + filter);
}

export function fetchSessionById(id) {
    return AXIOS.get('/sessions/' + id);
}

export function createRegistration(registrationData) {
    return AXIOS.post('/registrations/', registrationData);
}

export function fetchRegistrations() {
    return AXIOS.get('/registrations');
}

export function fetchRegistrationsByCustomerUsername(username) {
    return AXIOS.get('/registrations/customer/' + username);
}

export function deleteRegistation(registrationId) {
    return AXIOS.delete('/registrations/' + registrationId);
}

export function createCustomer(customer) {
    console.log("entereing api.js");
    return AXIOS.post('/customer', customer);
}

export function updateCustomerPassword(password) {
    return AXIOS.put(`/customer/password`, password);
}