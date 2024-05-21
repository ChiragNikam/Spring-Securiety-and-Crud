package com.example.learn.springcrud.repository;

import com.example.learn.springcrud.data.Role;
import com.example.learn.springcrud.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    User findByRole(Role role);

}
