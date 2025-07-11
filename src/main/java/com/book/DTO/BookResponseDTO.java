package com.book.DTO;

public class BookResponseDTO {
    private Long id;
    private String title;
    private String isbn;
    private String authorName;

    public String getAuthorName() {
            return authorName;
        }
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

}

