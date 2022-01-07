package com.alex.test_kata_3_1_1.repository;

import com.alex.test_kata_3_1_1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
