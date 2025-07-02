package com.book.DTO;

import jakarta.validation.constraints.NotBlank;

public class AuthorUpdateDTO {

    @NotBlank(message= "Name cannot be blank")
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
