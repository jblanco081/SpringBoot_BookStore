import React, { useEffect, useState } from 'react';
import { getAllAuthors } from '../services/authorService';
import AddAuthor from '../components/AddAuthor';


function AuthorList() {
  const [authors, setAuthors] = useState([]);

  const fetchAuthors = () => {
    getAllAuthors()
      .then((response) => {
        setAuthors(response.data || []);
      })
      .catch((error) => {
        console.error('Error fetching authors:', error);
      });
  }; 
  
  useEffect(() => {
    fetchAuthors();

  }, []);

  return (
    <div>
      <AddAuthor onAuthorAdded={fetchAuthors} />
      <h2>Author List</h2>
      {authors.length === 0 ? (
        <p>No authors found.</p>
      ) : (
        <ul>
          {authors.map((author) => (
            <li key={author.id}>
              {author?.name || 'No name'} 
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default AuthorList;
