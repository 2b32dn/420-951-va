package com.mybookstore.books.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor // Lombok annotation to generate a no-args constructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private double totalAmount; // Total amount for the order

    @Enumerated(EnumType.STRING) // Store the enum as a string in the database
    private OrderStatus status; // Status of the order (PLACED, COMPLETED, PROCESSING, DELIVERED). This is an enum defined in OrderStatus.java

    // Order Creation. Required for createOrder method in OrderService
    // One-to-many relationship with OrderItem
    // We map it to the "order" field in OrderItem
    // CascadeType.ALL means that all operations (persist, merge, remove, refresh, detach) will be cascaded to the order items
    // orphanRemoval = true means that if an order item is removed from the order, it will also be removed from the database
    // FetchType.EAGER means that order items will be fetched immediately when the order is fetched
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> orderItems = new HashSet<>(); // Set of order items associated with this order
}
