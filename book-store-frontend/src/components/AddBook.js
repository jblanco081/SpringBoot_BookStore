import React, { useState } from 'react';
import { createBook } from '../services/bookService';

const AddBook = () => {
  const [book, setBook] = useState({
    title: '',
    author: {
      name: ''
    },
    isbn: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;

    if (name === 'author') {
      setBook({ ...book, author: { name: value } });
    } else {
      setBook({ ...book, [name]: value });
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    createBook(book)
      .then(() => {
        alert('Book added successfully!');
        setBook({ title: '', author: { name: '' }, isbn: '' });
      })
      .catch((err) => console.error('Error adding book:', err));
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
          name="author"
          placeholder="Author Name"
          value={book.author.name}
          onChange={handleChange}
        />
        <input
          type="text"
          name="isbn"
          placeholder="ISBN"
          value={book.isbn}
          onChange={handleChange}
        />
        <button type="submit">Add Book</button>
      </form>
    </div>
  );
};

export default AddBook;
