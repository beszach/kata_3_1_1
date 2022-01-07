package com.alex.test_kata_3_1_1.service;

import com.alex.test_kata_3_1_1.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;


public interface UserService {
    User findByEmail(String email);
    void add(User model);
    void update(User model);
    void delete(Long id);
    List<User> getAll();
    User getById(Long id);
}
