package com.mybookstore.books.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Map the Book Class to a database table via JPA annotations in repostitory
@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor // Lombok annotation -> No need to write a no-args constructor
// @AllArgsConstructor //Lombok annotation -> No need to write an all-args
public class Book {

    // Same fields as in the books table in the database
    @Id // Primary key for the Book entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private Long id;

    private String title;
    private String author;
    private double price;
    private int stock;

    // Book Creation. Required for createBook method in BookService
    // Constructor without id (for creating new books)
    public Book(String title, String author, double price, int stock) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.stock = stock;
    }

    // No-args Constructor
    // public Book() {
    // this.id = null;
    // this.title = "";
    // this.author = "";
    // this.price = 0.0;
    // this.stock = 0;
    // }
    // All-args constructor
    // public Book(long id, String title, String author, double price, int stock) {
    // this.id = id;
    // this.title = title;
    // this.author = author;
    // this.price = price;
    // this.stock = stock;
    // }
}
