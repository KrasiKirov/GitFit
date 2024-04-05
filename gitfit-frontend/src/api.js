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

export function deleteFitnessClass(name) {
    const encodedName = encodeURIComponent(name);
    return AXIOS.delete(`/fitnessclasses/${encodedName}`);
}

export function fetchInstructors() {
    return AXIOS.get('/instructors/');
}

export function deleteInstructor(username) {
    return AXIOS.delete('/instructor/', { 
        data: username,
        headers: { 'Content-Type': 'text/plain' }
    });
}