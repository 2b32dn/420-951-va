package com.mybookstore.books.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mybookstore.books.controller.request.CartCreateRequest;
import com.mybookstore.books.controller.request.CartUpdateRequest;
import com.mybookstore.books.controller.response.CartResponse;
import com.mybookstore.books.service.CartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Example endpoint to get cart by user ID
    @GetMapping("/{userId}")
    public ResponseEntity<List<CartResponse>> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.findMyCart(userId).stream().map(CartResponse::toResponse).toList());
    }

    // Post endpoint to add a book to the cart can be added here
    @PostMapping("/{userId}/books")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCartItem(@PathVariable Long userId, @RequestBody @Valid CartCreateRequest cartRequest) {
        cartService.createCart(userId, cartRequest.getBookId());
    }

    @PutMapping("/{userId}/books/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCartItem(@PathVariable Long userId, @PathVariable Long bookId, @RequestBody @Valid CartUpdateRequest cartRequest) {
        cartService.updateCart(userId, bookId, cartRequest.getQuantity());
    }

    @DeleteMapping("/{userId}/books/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCartItem(@PathVariable Long userId, @PathVariable Long bookId) {
        cartService.delete(userId, bookId);
    }
}
