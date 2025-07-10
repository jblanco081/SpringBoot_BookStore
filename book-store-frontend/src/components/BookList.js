import React, { useEffect, useState } from 'react';
import { getAllBooks } from '../services/bookService';
import AddBook from "../components/AddBook";

function BookList() {
  const [books, setBooks] = useState([]);

  const fetchBooks = () => {
    getAllBooks()
      .then((response) => {
        setBooks(response.data);
      })
      .catch((error) => {
        console.error('Error fetching books:', error);
      });
  };

  useEffect(() => {
    fetchBooks();
  }, []);

  return (
    <div>
      <AddBook onBookAdded={fetchBooks} />
      <h2>Book List</h2>
      {books.length === 0 ? (
        <p>No books found.</p>
      ) : (
        <ul>
          {books.map((book) => (
            <li key={book?.id}>{book?.title} by {book?.authorName}</li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default BookList;
