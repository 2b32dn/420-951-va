package com.mybookstore.books.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mybookstore.books.model.Order;
import com.mybookstore.books.repository.OrderJPARepository;

@Service
public class OrderService {

    private final OrderJPARepository orderJPARepository;

    public OrderService(OrderJPARepository orderJPARepository) {
        this.orderJPARepository = orderJPARepository;
    }

    // Get all orders
    public List<Order> getOrders() {
        return orderJPARepository.findAll();
    }

    // Get single order
    public Optional<Order> getOrder(Long orderId) {
        return orderJPARepository.findById(orderId);
    }
}
