package com.book.Controller;

import java.util.Optional;
import java.util.List;

import com.book.Service.AuthorService;
import com.book.Model.Author;
import com.book.DTO.AuthorUpdateDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Operation(summary= "Adds a new author!")
    @PostMapping("/newAuthor")
    public ResponseEntity<String> addAuthor(@Valid @RequestBody Author author) {
        if (authorService.existsByName(author.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
            .body("Author already exists!");
        }

        Author savedAuthor = authorService.saveAuthor(author);
        return ResponseEntity.status(HttpStatus.CREATED)
        .body("Author " + savedAuthor + " has been added to System!");
    }

    @Operation(summary= "Gets all authors")
    @GetMapping("/authors")
    public List<Author> getAuthors() {
        return authorService.getAllAuthors();
    }

    @Operation(summary= "Gets specific author by name")
    @GetMapping("/authors/{name}")
    public ResponseEntity<Author> getAuthorByName(@PathVariable String name) {
        Optional<Author> author = authorService.getAuthorByName(name);
        return author.map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound()
        .build());
    }

    @Operation(summary= "Deletes author by id")
    @DeleteMapping("/authors/{id}")
    public ResponseEntity<String> deleteAuthorById(@PathVariable Long id) {
        Optional<Author> author = authorService.getAuthorById(id);
        if (author.isPresent()) {
        authorService.deleteAuthorById(id);
        return ResponseEntity.ok("Author deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("This author ID does not exist");
        }
    }

    @Operation(summary= "Update an existing author by ID")
    @PutMapping("authors/{id}")
    public ResponseEntity<String> updateAuthorById(@PathVariable Long id, 
    @Valid @RequestBody AuthorUpdateDTO updateAuthorDTO) {
        Optional<Author> authorOptional = authorService.getAuthorById(id);

        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            author.setName(updateAuthorDTO.getName());

            authorService.saveAuthor(author);

            return ResponseEntity.ok("Author updated: " + author.getName());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("Author not found!");
        }
    }
}
