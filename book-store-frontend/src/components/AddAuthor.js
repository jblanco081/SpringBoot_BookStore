import React, { useState } from 'react';
import { createAuthor } from '../services/authorService';

const AddAuthor = ({ onAuthorAdded }) => {
  const [name, setName] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    createAuthor({ name })
      .then(() => {
        alert('Author added successfully!');
        setName('');
        onAuthorAdded(); // refresh the author list
      })
      .catch(err => console.error("Error adding author:", err));
  };

  return (
    <form onSubmit={handleSubmit}>
      <h3>Add Author</h3>
      <input
        type="text"
        placeholder="Author Name"
        value={name}
        onChange={(e) => setName(e.target.value)}
        required
      />
      <button type="submit">Add Author</button>
    </form>
  );
};

export default AddAuthor;
