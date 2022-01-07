package com.alex.test_kata_3_1_1.service;

import com.alex.test_kata_3_1_1.model.Role;

import java.util.List;

public interface RoleService {
    void add(Role roleName);
    List<Role> getAll();
}
