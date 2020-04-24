package com.zpi.zpibackend.controller;

import com.zpi.zpibackend.entity.EventPerson;
import com.zpi.zpibackend.entity.Role;
import com.zpi.zpibackend.entity.dto.EventPersonDto;
import com.zpi.zpibackend.entity.dto.RoleDto;
import com.zpi.zpibackend.repository.RoleRepository;
import com.zpi.zpibackend.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping("/send")
    public String send(){
        return "okej";
    }

    @GetMapping("/all")
    public List<RoleDto> getAll(){
        List<Role> roles = (List<Role>) roleService.getAll();
        return roles.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/one/{id}")
    public RoleDto getOne(@PathVariable Integer id){
        Role role = roleService.getById(id);
        return modelMapper.map(role, RoleDto.class);
    }

    private RoleDto convertToDto(Role role){
        RoleDto roleDto = modelMapper.map(role, RoleDto.class);
//        List<EventPerson> eventPeople = role.getEventPeople();
//        List<EventPersonDto> eventPersonDtos = roleDto.getEventPeople();
//        eventPersonDtos.forEach(eventPersonDto -> eventPersonDto.setRoleid(roleDto.getRoleid()));
        return roleDto;
    }
}
