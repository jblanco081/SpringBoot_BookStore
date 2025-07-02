package com.book.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookUpdateDTO {

    @NotBlank(message= "Title must not be blank")
    private String title;

    @NotNull(message= "AuthorID must not be blank")
    private Long authorId;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public Long getAuthorId() {
        return this.authorId;
    }

    public void setAuthor(Long newAuthorId) {
        this.authorId = newAuthorId;
    }

}
