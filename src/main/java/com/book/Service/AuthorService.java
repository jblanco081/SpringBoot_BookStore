package com.book.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

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

    public Optional<Author> getAuthorByName(String name) {
        return authorRepository.findByName(name);
    }

    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }

}
