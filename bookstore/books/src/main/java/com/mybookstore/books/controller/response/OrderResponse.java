package com.mybookstore.books.controller.response;

import java.util.List;

import com.mybookstore.books.model.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    UserResponse user;
    double total;
    String status;
    List<OrderItemResponse> orderItems;

    public static OrderResponse toResponse(Order order) {
        return new OrderResponse(
                UserResponse.toResponse(order.getUser()),
                order.getTotalAmount(),
                order.getStatus().toString(),
                order.getOrderItems().stream().map(OrderItemResponse::toResponse).toList()
        );
    }
}
