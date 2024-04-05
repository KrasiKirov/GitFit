import { data } from 'autoprefixer';
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

export function fetchApprovedFitnessClasses() {
    return AXIOS.get('/fitnessclasses/approved');
}

export function fetchPendingFitnessClasses() {
    return AXIOS.get('/fitnessclasses/pending');
}

export function updateFitnessClassStatus(name, status) {
    return AXIOS.put(`/fitnessclasses/${name}/approval`, JSON.stringify(status), {
        headers: {
            'Content-Type': 'application/json'
        }
    });
}

export function fetchInstructors() {
    return AXIOS.get('/instructors/');
}

export function deleteInstructor(username) {
    return AXIOS.delete('/instructor/', { data: { username: username }, headers: { 'Content-Type': 'application/json' }});
}


