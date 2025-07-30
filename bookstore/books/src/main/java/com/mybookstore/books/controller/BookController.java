package com.mybookstore.books.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {
    // Define your endpoints here
    // For example, you can add methods to handle book-related requests

    public BookController() {
        // Constructor logic if needed
    }
    @GetMapping("/test")
    public ResponseEntity<String> getBooks() {
      return ResponseEntity.ok("dummy response for books");
    }
}
