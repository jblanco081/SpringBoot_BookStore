package com.book.Model;

import java.util.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message= "Name cannot be blank")
    private String name;

    @OneToMany(mappedBy= "author", cascade= CascadeType.ALL, orphanRemoval= true)
    private List<Book> books = new ArrayList<>();


    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long newId) {
        this.id = newId;
    }

    public List<Book> getBooks() {
        return this.books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
