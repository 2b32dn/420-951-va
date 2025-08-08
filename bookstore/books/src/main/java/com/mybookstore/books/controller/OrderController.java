package com.mybookstore.books.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybookstore.books.controller.response.OrderResponse;
import com.mybookstore.books.service.OrderService;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Get all orders
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrders() {
        return ResponseEntity.ok(orderService.getOrders().stream().map(OrderResponse::toResponse).toList());
    }

    // Get single order by ID
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrder(orderId).map(OrderResponse::toResponse).orElse(null));
    }

    // Place a new order
    // @PostMapping
    // public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest order){
    //     return ResponseEntity.ok(orderService.placeOrder(order.getUserId()));
    // }
}
