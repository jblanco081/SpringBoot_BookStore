package com.book.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.book.Model.Book;
import com.book.Repository.BookRepository;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public boolean existsByTitle(String title) {
        return bookRepository.existsByTitle(title);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBook(Long id) {
        return bookRepository.findById(id);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public Optional<Book> updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id).map(existingBook -> {
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            return bookRepository.save(existingBook);
        });
    }

    public List<Book> searchByAuthorName(String name) {
        return bookRepository.findByAuthor_NameContainingIgnoreCase(name);
    }
 
}
