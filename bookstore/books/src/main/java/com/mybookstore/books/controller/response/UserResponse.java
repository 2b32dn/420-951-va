package com.mybookstore.books.controller.response;

import com.mybookstore.books.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
  Long userId;
  String name;
  String email;

  public static UserResponse toResponse(User user) {
    return new UserResponse(user.getId(), String.format("%s %s", user.getFirstName(), user.getLastName()), user.getEmail());
  }
}
