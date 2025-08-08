package com.mybookstore.books.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mybookstore.books.controller.request.BookRequest;
import com.mybookstore.books.controller.request.CreateGroup;
import com.mybookstore.books.controller.request.UpdateGroup;
import com.mybookstore.books.controller.response.BookResponse;
import com.mybookstore.books.service.BookService;

@Validated
// @Validated annotation is used to validate the request body
// It allows you to specify validation groups like CreateGroup and UpdateGroup
// This is useful for differentiating validation rules for different operations
@RestController
@RequestMapping("/books")
public class BookController {
    // Define your endpoints here
    // For example, you can add methods to handle book-related requests

    private final BookService bookService;

    public BookController(BookService bookService) {
        // Constructor injection of BookService
        this.bookService = bookService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("dummy response for books");
    }

    // Get All Books
    @GetMapping
    public ResponseEntity<List<BookResponse>> getBooks() {
        return ResponseEntity.ok(bookService.getBooks().stream().map(BookResponse::toResponse).toList());
    }

    // Get A Single book by title through RequestParam
    @GetMapping("/search")
    public ResponseEntity<List<BookResponse>> searchBooksByTitle(@RequestParam(name = "title") String title) {
        if (title.isBlank()) {
            return ResponseEntity.badRequest().build(); // or return all books instead
        }
        return ResponseEntity.ok(bookService.getBooksByTitle(title).stream().map(BookResponse::toResponse).toList()
        );
    }

    // Combination of the 2 methods above
    // @GetMapping
    // public ResponseEntity<List<BookResponse>> getBooks(@RequestParam(name="title", required= false)String title) {
    //  if(title == null || title.isBlank()) {
    //   return ResponseEntity.ok(bookService.getBooks().stream().map(BookResponse::toResponse).toList());
    //  }
    //  return ResponseEntity.ok(bookService.getBooksByTitle(title).stream().map(BookResponse::toResponse).toList()); // method getBooksByTitle is in BookService
    // }
    // Get A Single Book by ID through PathVariable
    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponse> getBook(@PathVariable Long bookId) {
        return bookService.getBook(bookId).map(book -> ResponseEntity.ok(BookResponse.toResponse(book))).orElse(ResponseEntity.notFound().build());
    }

    // Create a new book into the database
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBook(@RequestBody @Validated(CreateGroup.class) BookRequest bookRequest) { // @Validated annotation is used to validate the request body. See CreateGroup interface
        bookService.createBook(bookRequest.getTitle(),
                bookRequest.getAuthor(),
                bookRequest.getPrice(),
                bookRequest.getStock());
    }

    // Update an existing book
    @PutMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@PathVariable Long bookId, @RequestBody @Validated(UpdateGroup.class) BookRequest bookRequest) {
        bookService.updateBook(bookId, bookRequest.getTitle(),
                bookRequest.getAuthor(),
                bookRequest.getPrice(),
                bookRequest.getStock());
    }
}
