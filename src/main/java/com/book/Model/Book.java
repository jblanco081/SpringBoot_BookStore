package com.book.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @ManyToOne
    @JoinColumn(name= "author_id")
    private Author author;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public Author getAuthor() {
        return this.author;
    }

    public void setAuthor(Author newAuthor) {
        this.author = newAuthor;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long newId) {
        this.id = newId;
    }

}
