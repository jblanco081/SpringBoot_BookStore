import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './components/Home';
import EditBook from './components/EditBook';
import EditAuthor from './components/EditAuthor';
import './App.css';

function App() {
  return (
    <Router>
      <div className="App">
        <header>
          <h1>📚 Julian’s Book Store</h1>
          <p>Manage your books and authors</p>
        </header>

        <main>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/edit-book/:id" element={<EditBook />} />
            <Route path="/edit-author/:id" element={<EditAuthor />} />
          </Routes>
        </main>

        <footer>
          <p>© 2025 Julian Blanco</p>
        </footer>
      </div>
    </Router>
  );
}

export default App;
