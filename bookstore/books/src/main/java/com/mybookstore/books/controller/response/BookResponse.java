package com.mybookstore.books.controller.response;

import com.mybookstore.books.model.Book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookResponse {
  private String bookName;
  private String authorName;
  private double price;
  private int stock;

  public static BookResponse toResponse(Book book) {
    return new BookResponse(book.getTitle(), book.getAuthor(), book.getPrice(), book.getStock());
  }
}
