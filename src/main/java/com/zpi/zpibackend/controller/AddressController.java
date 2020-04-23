package com.zpi.zpibackend.controller;

import com.zpi.zpibackend.entity.Address;
import com.zpi.zpibackend.entity.Event;
import com.zpi.zpibackend.entity.EventPerson;
import com.zpi.zpibackend.entity.Role;
import com.zpi.zpibackend.entity.dto.AddressDto;
import com.zpi.zpibackend.entity.dto.EventDto;
import com.zpi.zpibackend.entity.dto.EventPersonDto;
import com.zpi.zpibackend.entity.dto.RoleDto;
import com.zpi.zpibackend.service.AddressService;
import com.zpi.zpibackend.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;
    @Autowired
    private EventService eventService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public List<AddressDto> getAll(){
        List<Address> addresses = addressService.getAll();
        return addresses.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private AddressDto convertToDto(Address address){
        return modelMapper.map(address, AddressDto.class);
    }

    private Address convertFromDto(AddressDto addressDto){
        return modelMapper.map(addressDto, Address.class);
    }

}
