package com.mybookstore.books.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mybookstore.books.model.Book;
import com.mybookstore.books.repository.BookJPARepository;

@Service
public class BookService {
  
  // This service class will handle business logic related to books
  // It will use the BookJPARepository to interact with the database
  // You can add methods to fetch, save, update, or delete books
  // Constructor injection is used to inject the repository dependency
  private final BookJPARepository bookRepository;
  public BookService(BookJPARepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  // Example method to get all books
  public List<Book> getBooks() {
    return bookRepository.findAll();
  }
  // Example method to get a book by ID
  // Optional is used to handle cases where the book might not be found
  // Return type is Optional<Book> to avoid NullPointerException
  public Optional<Book> getBook(Long bookId) {
    return bookRepository.findById(bookId);
  }
  // Without Optional, you could return null if the book is not found
  // public <Book> getBook(Long bookId) {
  //   return bookRepository.findById(bookId).orElse(null);
  // }
  
  // findByTitleContainingIgnoreCase method is also in BookJPARepository
  public List<Book> getBooksByTitle(String title) {
    return bookRepository.findByTitleContainingIgnoreCase(title);
  }
}
