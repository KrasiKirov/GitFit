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