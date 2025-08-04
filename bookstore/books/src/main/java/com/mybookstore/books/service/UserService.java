package com.mybookstore.books.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mybookstore.books.model.User;
import com.mybookstore.books.repository.UserJPARepository;

@Service
public class UserService {
  private final UserJPARepository repository;

  public UserService(UserJPARepository repository) {
    this.repository = repository;
  }
  public List<User> findAllUsers(){
        return repository.findAll();
    }

    public Optional<User> findUserByUserId(Long userId) {
        return repository.findById(userId);
    }

    public void deleteUserByUserId(Long userId){
        repository.deleteById(userId);
    }
}
