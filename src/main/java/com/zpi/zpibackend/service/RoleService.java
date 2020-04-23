package com.zpi.zpibackend.service;

import com.zpi.zpibackend.entity.Role;
import com.zpi.zpibackend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role addRole(Role role){
        return roleRepository.save(role);
    }
}
