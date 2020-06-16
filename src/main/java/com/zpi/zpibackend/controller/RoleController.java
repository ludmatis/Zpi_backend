package com.zpi.zpibackend.controller;

import com.zpi.zpibackend.entity.Event;
import com.zpi.zpibackend.entity.EventPerson;
import com.zpi.zpibackend.entity.Role;
import com.zpi.zpibackend.entity.dto.EventPersonDto;
import com.zpi.zpibackend.entity.dto.RoleDto;
import com.zpi.zpibackend.repository.RoleRepository;
import com.zpi.zpibackend.service.EventPersonService;
import com.zpi.zpibackend.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin()
@RestController()
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private EventPersonService eventPersonService;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping("/send")
    public String send(){
        return "okej";
    }

    @GetMapping("/all")
    public ResponseEntity getAll() {
        List<Role> roles = roleService.getAll();
        if (roles.isEmpty())
            return ResponseEntity.badRequest().body("Brak ról w bazie");
        else {
            List<RoleDto> roleDtos = roles.stream().map(this::convertToDto).collect(Collectors.toList());
            return new ResponseEntity<>(roleDtos, HttpStatus.OK);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getRoleById(@PathVariable Integer id){
        Role role = roleService.getById(id);
        if(role==null)
            return ResponseEntity.badRequest().body("Rola nie istnieje");
        else {
            return new ResponseEntity<>(convertToDto(role), HttpStatus.OK);
        }
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody RoleDto roleDto){
        Role role = convertFromDto(roleDto);
        if(roleService.add(role)==null) {
            return ResponseEntity.badRequest().body("Cos poszlo nie tak przy dodawaniu");
        }
        else{
            return new ResponseEntity<>(convertToDto(role),HttpStatus.OK);
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@RequestBody RoleDto roleDto, @PathVariable Integer id){
        Role role = roleService.getById(id);
        role.setRoleid(id);
        if(role==null)
            return ResponseEntity.badRequest().body("Rola nie istnieje");
        else{
            Role updated = roleService.update(convertFromDto(roleDto));
            return new ResponseEntity<>(convertToDto(updated),HttpStatus.OK);
        }

    }


    private RoleDto convertToDto(Role role){
        return modelMapper.map(role, RoleDto.class);
    }
    private Role convertFromDto(RoleDto roleDto) {
        Role role =modelMapper.map(roleDto,Role.class);

        List<EventPerson> allEventPeople = eventPersonService.getAll();
        List<EventPerson> exactEventPeople = new ArrayList<>();
        allEventPeople.forEach(eventPerson -> {
            if (eventPerson.getRole().getRoleid()==roleDto.getRoleid())
                exactEventPeople.add(eventPerson);
        });
        role.setEventPeople(exactEventPeople);

        return role;
    }
}
