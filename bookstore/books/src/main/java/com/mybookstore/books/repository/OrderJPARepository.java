package com.mybookstore.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybookstore.books.model.Order;

public interface OrderJPARepository extends JpaRepository<Order, Long> {
    // This interface extends JpaRepository, which provides basic CRUD operations for the Order entity.
    // Additional custom query methods can be defined here if needed.
}
