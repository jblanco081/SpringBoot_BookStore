import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/books'; // Update if needed

export const getAllBooks = () => {
  return axios.get(API_BASE_URL);
};

export const createBook = (book) => axios.post(API_BASE_URL, book);


const bookService = { getAllBooks, createBook };

export default bookService;