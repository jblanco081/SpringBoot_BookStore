import React from 'react';
import BookList from './components/BookList';
import AddBook from './components/AddBook';

import './App.css'

function App() {
  return (
    <div className="App">
      <header>
        <h1>📚 Julian’s Book Store</h1>
        <p>Browse all available books</p>
      </header>

      <main>
        <AddBook />
        <BookList />
      </main>

      <footer>
        <p>© 2025 Julian Blanco</p>
      </footer>
    </div>
  );
}

export default App;
