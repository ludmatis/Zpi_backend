package com.zpi.zpibackend.service;

import com.zpi.zpibackend.entity.Role;
import com.zpi.zpibackend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role add(Role role){
        return roleRepository.save(role);
    }
    public List<Role> getAll(){
        return (List<Role>) roleRepository.findAll();
    }
    public Role getById(Integer id){
        return roleRepository.findById(id).orElse(null);
    }

    public Role update(Role role){
        return roleRepository.save(role);
    }

}
