package com.mybookstore.books.model;

public enum OrderStatus {
    PLACED, // Order has been placed but not yet processed
    CANCELLED,
    PROCESSING,
    DELIVERED
}
