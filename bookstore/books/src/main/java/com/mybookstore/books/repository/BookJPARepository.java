package com.mybookstore.books.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybookstore.books.model.Book;

public interface BookJPARepository extends JpaRepository<Book, Long> {
    // This interface will automatically provide CRUD operations for the Book entity
    // No need to implement any methods, Spring Data JPA will handle it
    List<Book> findByTitleContainingIgnoreCase(String title);

}
