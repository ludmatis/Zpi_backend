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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get")
    public ResponseEntity getAll(){
        List<Address> addresses = addressService.getAll();
        if(addresses.isEmpty()){
            return ResponseEntity.badRequest().body("Brak adresów w bazie");
        }
        else{
            List<AddressDto> addressDtos =  addresses.stream().map(this::convertToDto).collect(Collectors.toList());
            return new ResponseEntity<>(addressDtos, HttpStatus.OK);
        }
    }


    @PostMapping("/add")
    public ResponseEntity addAddress(@RequestBody AddressDto addressDto) {
        Address address = convertFromDto(addressDto);
        if(addressService.add(address) == null){
            return ResponseEntity.badRequest().body("Cos poszlo nie tak przy dodawaniu");
        }
        return new ResponseEntity<>(convertToDto(address), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateAddress(@RequestBody AddressDto addressDto, @PathVariable Integer id){
        Address address = addressService.getById(id);
        addressDto.setAddressid(id);
        if(address == null){
            return ResponseEntity.badRequest().body("Podany adres nie istnieje");
        }
        addressService.update(convertFromDto(addressDto));
        return new ResponseEntity<>(addressDto, HttpStatus.OK);
    }

    private AddressDto convertToDto(Address address){
        return modelMapper.map(address, AddressDto.class);
    }

    private Address convertFromDto(AddressDto addressDto){
        return modelMapper.map(addressDto, Address.class);
    }

}
