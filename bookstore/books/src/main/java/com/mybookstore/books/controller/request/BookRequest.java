package com.mybookstore.books.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data // This annotation generates getters, setters, toString, equals, and hashCode methods
public class BookRequest {

    // Book Creation. This class is used to handle book creation requests
    // Validation groups for different operations
    @NotBlank
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters") // Validation for title
    private String title;
    @NotNull(groups = UpdateGroup.class) // Author is required for updates, but not for creation. See CreateGroup and UpdateGroup interfaces
    private String author;
    @NotNull(groups = UpdateGroup.class)
    private double price;
    @NotNull(groups = UpdateGroup.class)
    private int stock;
}
