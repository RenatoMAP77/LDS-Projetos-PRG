import axios from 'axios';
const BASE_URL = process.env.URL_BASE;

const api = axios.create({
  baseURL: BASE_URL,
});

export default api;