import React from 'react';
import BookList from './components/BookList';
import AuthorList from './components/AuthorList';

import './App.css'

function App() {
  return (
    <div className="App">
      <header>
        <h1>📚 Julian’s Book Store</h1>
        <p>Manage your books and authors</p>
      </header>

      <main>
        <section>
        <BookList />
        </section>

        <hr />

        <section>
          {<AuthorList />}
        </section>
      </main>

      <footer>
        <p>© 2025 Julian Blanco</p>
      </footer>
    </div>
  );
}

export default App;
