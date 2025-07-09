import React, { useEffect, useState } from 'react';
import { getAllBooks } from '../services/bookService';

const BookList = () => {
  const [books, setBooks] = useState([]);

  useEffect(() => {
  getAllBooks()
    .then(response => setBooks(response.data))
    .catch(error => console.error("Failed to fetch books:", error));
}, []);

  return (
    <div>
      <h2>Book List</h2>
      <table border="1">
        <thead>
          <tr>
            <th>Title</th>
            <th>Author Name</th>
            <th>ISBN</th>
          </tr>
        </thead>
        <tbody>
            {books.map(book => (
                <tr key={book.id}>
                <td>{book.title}</td>
                <td>{book.author?.name || 'N/A'}</td>
                <td>{book.isbn || 'N/A'}</td>
                </tr>
            ))}
        </tbody>
      </table>
    </div>
  );
};

export default BookList;
