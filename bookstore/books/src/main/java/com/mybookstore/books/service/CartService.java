package com.mybookstore.books.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mybookstore.books.model.Book;
import com.mybookstore.books.model.Cart;
import com.mybookstore.books.model.User;
import com.mybookstore.books.repository.CartJPARepository;
import com.mybookstore.books.repository.BookJPARepository;
import com.mybookstore.books.repository.UserJPARepository;

import jakarta.transaction.Transactional;

@Service
public class CartService {

    private final CartJPARepository cartJPARepository;
    private final BookJPARepository bookJPARepository;
    private final UserJPARepository userJPARepository;

    public CartService(CartJPARepository cartJPARepository, UserJPARepository userJPARepository, BookJPARepository bookJPARepository) {
        this.cartJPARepository = cartJPARepository;
        this.bookJPARepository = bookJPARepository;
        this.userJPARepository = userJPARepository;
    }

    public List<Cart> findCartByUserId(Long userId) {
        User user = userJPARepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User is not found"));
        return cartJPARepository.findByUser(user);
    }

    public List<Cart> findMyCart(Long userId) {
        User user = userJPARepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User is not found"));
        return cartJPARepository.findByUser(user);
    }

    public void createCart(Long userId, Long bookId) {
        User user = userJPARepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user is not found"));
        Book book = bookJPARepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("book is not found"));

        Cart cart;
        Optional<Cart> cartOptional = cartJPARepository.findByUserAndBook(user, book);
        if (cartOptional.isPresent()) {
            cart = cartOptional.get();
            cart.setQuantity(cart.getQuantity() + 1);
        } else {
            cart = new Cart(user, book);
        }
        cartJPARepository.save(cart);
    }

    public void updateCart(Long userId, Long bookId, int quantity) {
        User user = userJPARepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user is not found"));
        Book book = bookJPARepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("book is not found"));
        Cart cart = cartJPARepository.findByUserAndBook(user, book)
                .orElseThrow(() -> new RuntimeException("cart is not found"));

        cart.setQuantity(quantity);
        cartJPARepository.save(cart);
    }

    @Transactional
    public void delete(Long userId, Long bookId) {
        cartJPARepository.deleteCartItem(userId, bookId);
    }

    public void clearCart(Long userId) {
        User user = userJPARepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user is not found"));
        cartJPARepository.deleteByUser(user);
    }
}
