package com.book.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.DTO.BookCreateDTO;
import com.book.DTO.BookResponseDTO;
import com.book.DTO.BookUpdateDTO;
import com.book.Model.Author;
import com.book.Model.Book;
import com.book.Service.AuthorService;
import com.book.Service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookController {
    
    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Operation(summary= "Adds a new book")
    @PostMapping()
    public ResponseEntity<String> addBook(@Valid @RequestBody BookCreateDTO bookCreateDTO) {
        if (bookService.existsByTitle(bookCreateDTO.getTitle())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
            .body("Book with title " + bookCreateDTO.getTitle() + " already exists.");
        }

        Author author = authorService.getAuthorById(bookCreateDTO.getAuthorId());

        Book book = new Book();
        book.setTitle(bookCreateDTO.getTitle());
        book.setISBN(bookCreateDTO.getISBN());
        book.setAuthor(author);

        Book savedBook = bookService.saveBook(book);

        return ResponseEntity.status(HttpStatus.CREATED)
        .body(savedBook.getTitle() + " has been added to the book list!");
    }

    @Operation(summary = "Gets all books")
    @GetMapping()
    public List<BookResponseDTO> getBooks() {
        return bookService.getAllBooks()
        .stream()
        .map(book -> {
            BookResponseDTO dto = new BookResponseDTO();
            dto.setId(book.getId());
            dto.setTitle(book.getTitle());
            dto.setIsbn(book.getISBN());
            dto.setAuthorName(book.getAuthor() != null ? book.getAuthor().getName() : "Unknown");
            return dto;
        })
        .collect(Collectors.toList());
    }

    @Operation(summary= "Gets a specific book by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        Book book = bookService.getBook(id);
        return ResponseEntity.ok(book);
    }

    @Operation(summary= "Gets all books from a specific author")
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooksByAuthorName(@RequestParam String name) {
        List<Book> books = bookService.searchByAuthorName(name);

        return ResponseEntity.ok(books);
    }

    @Operation(summary= "Deletes a specific book by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        Book book = bookService.getBook(id);
        bookService.deleteBookById(id);

        return ResponseEntity.ok("Book deleted successfully!");
    
    }

    @Operation(summary= "Update an existing book by ID")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBookById(
        @PathVariable Long id, 
        @Valid @RequestBody BookUpdateDTO updatedBookDTO) {
        
        Book book = bookService.getBook(id);
        
        book.setTitle(updatedBookDTO.getTitle());
        book.setISBN(updatedBookDTO.getISBN());

        Author author = authorService.getAuthorById(updatedBookDTO.getAuthorId());

        book.setAuthor(author);

        bookService.saveBook(book);
            
        return ResponseEntity.ok("Book updated: " + book.getTitle());
        
        }

    }
    
