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
import org.springframework.web.bind.annotation.RestController;

import com.book.DTO.AuthorResponseDTO;
import com.book.DTO.AuthorUpdateDTO;
import com.book.Model.Author;
import com.book.Service.AuthorService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        
        this.authorService = authorService;
    }

    @Operation(summary= "Adds a new author!")
    @PostMapping()
    public ResponseEntity<String> addAuthor(@Valid @RequestBody Author author) {
        
        Author savedAuthor = authorService.saveAuthor(author);
        return ResponseEntity.status(HttpStatus.CREATED)
        .body("Author " + savedAuthor.getName() + " has been added to System!");
    }

    @Operation(summary= "Gets all authors")
    @GetMapping()
    public List<AuthorResponseDTO> getAuthors() {
        
        return authorService.getAllAuthors()
        .stream()
        .map(author -> {
            AuthorResponseDTO dto = new AuthorResponseDTO();
            dto.setName(author.getName());
            dto.setID(author.getId());
            return dto;
        })
        .collect(Collectors.toList());
    }

    @Operation(summary= "Gets specific author by name")
    @GetMapping("/{name}")
    public ResponseEntity<Author> getAuthorByName(@PathVariable String name) {
        
        Author author = authorService.getAuthorByName(name);
        
        return ResponseEntity.ok(author);
    }

    @Operation(summary= "Deletes author by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthorById(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
        
        authorService.deleteAuthorById(id);

        return ResponseEntity.ok("Author deleted successfully!");
    }

    @Operation(summary= "Update an existing author by ID")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAuthorById(@PathVariable Long id, 
    @Valid @RequestBody AuthorUpdateDTO updateAuthorDTO) {
        Author author = authorService.getAuthorById(id);

        author.setName(updateAuthorDTO.getName());

        authorService.saveAuthor(author);

        return ResponseEntity.ok("Author updated: " + author.getName());
        
    }
}
