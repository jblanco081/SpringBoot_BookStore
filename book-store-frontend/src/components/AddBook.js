import React, { useState, useEffect } from 'react';
import { createBook } from '../services/bookService';
import { getAllAuthors } from '../services/authorService';

const AddBook = ({ onBookAdded }) => {
  const [book, setBook] = useState({
    title: '',
    authorId: '',
    isbn: ''
  });

  const [authors, setAuthors] = useState([]);

  useEffect(() => {
  refreshAuthors();
}, []);

const refreshAuthors = () => {
  getAllAuthors()
    .then(response => setAuthors(response.data))
    .catch(error => console.error('Error fetching authors:', error));
};

  const handleChange = (e) => {
    const { name, value } = e.target;
    setBook(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const payload = {
      title: book.title,
      isbn: book.isbn,
      authorId: Number(book.authorId)
    };

    createBook(payload)
      .then(() => {
        alert('Book added successfully!');
        setBook({ title: '', authorId: '', isbn: '' });
        if (onBookAdded) onBookAdded();
      })
      .catch(err => console.error('Error adding book:', err));
  };

  return (
    <div>
      <h2>Add New Book</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="title"
          placeholder="Title"
          value={book.title}
          onChange={handleChange}
        />
        <input
          type="text"
          name="isbn"
          placeholder="ISBN"
          value={book.isbn}
          onChange={handleChange}
        />
        <select
          name="authorId"
          value={book.authorId}
          onChange={handleChange}
        >
          <option value="">Select Author</option>
          {authors.map((author) => (
            <option key={author.id} value={author.id}>
              {author.name} (ID: {author.id})
            </option>
          ))}
        </select>
        <button type="submit">Add Book</button>
      </form>
    </div>
  );
};

export default AddBook;
