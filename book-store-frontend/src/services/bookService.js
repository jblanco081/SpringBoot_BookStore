import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/books'; // Update if needed

export const getAllBooks = () => {
  return axios.get(API_BASE_URL);
};
