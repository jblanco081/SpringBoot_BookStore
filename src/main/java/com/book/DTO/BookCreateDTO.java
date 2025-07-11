package com.book.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookCreateDTO {

    @NotBlank(message= "Title must not be blank")
    private String title;

    @NotNull(message= "AuthorID must not be blank")
    private Long authorId;

    @NotNull(message = "ISBN must not be blank")
    private String isbn;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public String getISBN() {
        return this.isbn;
    }

    public void setISBN(String newISBN) {
        this.isbn = newISBN;
    } 

    public Long getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(Long newAuthorId) {
        this.authorId = newAuthorId;
    }



}
