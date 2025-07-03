package com.book.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.book.Exception.AuthorNotFoundException;
import com.book.Model.Author;
import com.book.Repository.AuthorRepository;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public boolean existsByName(String name) {
        return authorRepository.existsByName(name);
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author getAuthorByName(String name) {
        return authorRepository.findByName(name)
        .orElseThrow(() -> new AuthorNotFoundException("Author with name " + name + " not found"));
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
        .orElseThrow(() -> new AuthorNotFoundException("Author with ID " + id + " not found"));
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }

}
