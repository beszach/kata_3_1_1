package com.alex.test_kata_3_1_1.service;


import com.alex.test_kata_3_1_1.model.Role;
import com.alex.test_kata_3_1_1.model.User;
import com.alex.test_kata_3_1_1.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceDataBase implements RoleService{

    private final RoleRepository roleRepository;

    public RoleServiceDataBase(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    @Override
    public void add(Role role) {
        roleRepository.saveAndFlush(role);
    }


    @Transactional
    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

}
