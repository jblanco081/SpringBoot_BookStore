import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/authors';

export const getAllAuthors = () => axios.get(API_BASE_URL);

export const createAuthor = (author) => axios.post(API_BASE_URL, author);

export const deleteAuthor = (id) => axios.delete(`${API_BASE_URL}/${id}`);

const authorService = {getAllAuthors, createAuthor, deleteAuthor};

export default authorService;