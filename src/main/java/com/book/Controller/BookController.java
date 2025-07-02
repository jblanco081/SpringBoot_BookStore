package com.book.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.book.DTO.BookCreateDTO;
import com.book.DTO.BookUpdateDTO;
import com.book.Model.Author;
import com.book.Model.Book;
import com.book.Service.AuthorService;
import com.book.Service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping()
public class BookController {
    
    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Operation(summary= "Adds a new book")
    @PostMapping("/newBook")
    public ResponseEntity<String> addBook(@Valid @RequestBody BookCreateDTO bookCreateDTO) {
        if (bookService.existsByTitle(bookCreateDTO.getTitle())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
            .body("Title already exists!");
        }

        Optional<Author> authorOptional = authorService.getAuthorById(bookCreateDTO.getAuthorId());
            
        if (authorOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("Invalid Author ID provided.");
        }

        Book book = new Book();
        book.setTitle(bookCreateDTO.getTitle());
        book.setAuthor(authorOptional.get());

        Book savedBook = bookService.saveBook(book);

        return ResponseEntity.status(HttpStatus.CREATED)
        .body(savedBook.getTitle() + " has been added to the book list!");
    }

    @Operation(summary = "Gets all books")
    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }

    @Operation(summary= "Gets a specific book by its ID")
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        Optional<Book> book = bookService.getBook(id);
        return book.map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound()
        .build());
    }

    @Operation(summary= "Gets all books from a specific author")
    @GetMapping("/books/search")
    public ResponseEntity<List<Book>> searchBooksByAuthorName(@RequestParam String name) {
        List<Book> books = bookService.searchByAuthorName(name);

        if(books.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(List.of());
        }

        return ResponseEntity.ok(books);
    }

    @Operation(summary= "Deletes a specific book by its ID")
    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        Optional <Book> book = bookService.getBook(id);
        if (book.isPresent()) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok("Book deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("This book ID does not exist");
        }
    }

    @Operation(summary= "Update an existing book by ID")
    @PutMapping("books/{id}")
    public ResponseEntity<String> updateBookById(
        @PathVariable Long id, 
        @Valid @RequestBody BookUpdateDTO updatedBookDTO) {
        
        Optional<Book> bookOptional = bookService.getBook(id);
        
        if (bookOptional.isPresent()) {
           
            Book book = bookOptional.get();
            book.setTitle(updatedBookDTO.getTitle());

            Optional<Author> authorOptional = authorService.getAuthorById(updatedBookDTO.getAuthorId());
            
            if (authorOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Invalid Author ID provided.");
            }

            book.setAuthor(authorOptional.get());

            
            bookService.saveBook(book);
            
            return ResponseEntity.ok("Book updated: " + book.getTitle());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Book not found!");
        }
    }
    

}
