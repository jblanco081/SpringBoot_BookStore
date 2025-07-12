// components/Home.js
import React from 'react';
import AuthorList from '../components/AuthorList';
import BookList from '../components/BookList';

const Home = () => (
  <>
    <AuthorList />
    <hr />
    <BookList />
  </>
);

export default Home;
