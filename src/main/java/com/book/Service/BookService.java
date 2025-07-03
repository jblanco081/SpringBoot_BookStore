package com.book.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.book.Exception.BookNotFoundException;
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

    public Book getBook(Long id) {
        return bookRepository.findById(id)
        .orElseThrow(() -> new BookNotFoundException("Book with id " + id + " was not found"));
    }

    public void deleteBookById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book with ID " + id + " was not found");
        }
        
        bookRepository.deleteById(id);
    }

    public Book updateBook(Long id, Book updatedBook) {
        Book existingBook = bookRepository.findById(id)
        .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " was not found"));
        
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        return bookRepository.save(existingBook);
        
    }

    public List<Book> searchByAuthorName(String name) {
        return bookRepository.findByAuthor_NameContainingIgnoreCase(name);
    }
 
}
