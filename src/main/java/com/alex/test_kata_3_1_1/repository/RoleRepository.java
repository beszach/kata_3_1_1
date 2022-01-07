package com.alex.test_kata_3_1_1.repository;


import com.alex.test_kata_3_1_1.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
