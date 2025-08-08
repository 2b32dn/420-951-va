package com.mybookstore.books.controller.response;

import com.mybookstore.books.model.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponse {

    Long id;
    BookResponse book;
    int quantity;
    double price;

    public static OrderItemResponse toResponse(OrderItem order) {
        return new OrderItemResponse(
                order.getId(),
                BookResponse.toResponse(order.getBook()),
                order.getQuantity(),
                order.getPriceAtPurchase()
        );
    }
}
