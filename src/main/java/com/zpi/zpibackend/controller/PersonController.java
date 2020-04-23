package com.zpi.zpibackend.controller;

import com.zpi.zpibackend.entity.Person;
import com.zpi.zpibackend.entity.dto.PersonDto;
import com.zpi.zpibackend.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public List<PersonDto> getAll(){
        List<Person> people = personService.getAll();
        return people.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private PersonDto convertToDto(Person person){
        return modelMapper.map(person, PersonDto.class);
    }

    //TODO if entity contains list of other entities convertFromDTO needs to be updated
    private Person convertFromDto(PersonDto personDto){
        return modelMapper.map(personDto, Person.class);
    }
}
