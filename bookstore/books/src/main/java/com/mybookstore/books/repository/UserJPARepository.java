package com.mybookstore.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mybookstore.books.model.User;



public interface UserJPARepository extends JpaRepository<User, Long> {
  User findByUserName(String username);

  @Modifying
  @Query("delete from User u where u.email = :email")
  void deleteByEmail(@Param("email") String email);
  
  @Modifying
  @Query(value = "delete from users u where u.email = :email", nativeQuery = true)
  void deleteByEmailNative(@Param("email") String email);
}
