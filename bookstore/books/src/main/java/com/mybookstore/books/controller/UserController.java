package com.mybookstore.books.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mybookstore.books.controller.response.UserResponse;
import com.mybookstore.books.service.UserService;

@RestController
public class UserController {
  private final UserService service;

  public UserController(UserService service) {
    this.service = service;
  }

  @GetMapping("/api/users")
  public ResponseEntity<List<UserResponse>> getAllUsers() {
    return ResponseEntity.ok(service.findAllUsers().stream().map(UserResponse::toResponse).toList());
  }

  @GetMapping("/api/users/{userId}")
  public ResponseEntity<UserResponse> getUser(@PathVariable Long userId) {
    return ResponseEntity.ok(service.findUserByUserId(userId).map(UserResponse::toResponse).orElse(null));
  }
}
