package com.zpi.zpibackend.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.zpi.zpibackend.entity.Person;
import com.zpi.zpibackend.entity.dto.PersonDto;
import com.zpi.zpibackend.service.PersonService;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.notFound;

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

    @GetMapping("/person/{id}")
    public ResponseEntity getPersonById(@PathVariable Integer id){
        Person person = personService.getByID(id);
        if(person == null){
            return ResponseEntity.badRequest().body("Użytkownik nie istnieje");
        }
        else return new ResponseEntity<>(convertToDto(person),HttpStatus.OK);
    }
    @GetMapping("/getperson/{email}/{password}")
    public ResponseEntity getPersonByEmailAndPassword(@PathVariable String email, @PathVariable String password){
        Person person = personService.getByEmail(email);
        Gson gson = new Gson();
        if(person == null){
            return ResponseEntity.badRequest().body(("Użytkownik z takim emailem nie istnieje"));
        }
        if(person.getPassword().equals(password)){
            return new ResponseEntity<>(convertToDto(person), HttpStatus.OK);
        }
        else return ResponseEntity.badRequest().body("Złe hasło");

    }
    @PostMapping("/addperson")
    public ResponseEntity addPerson(@RequestBody PersonDto personDto){
        Person person = modelMapper.map(personDto, Person.class);
        if(personService.getByEmail(person.getEmail()) != null){
            return ResponseEntity.badRequest().body("Podany email jest zajęty");
        }
        else{
            return new ResponseEntity<>(personDto, HttpStatus.OK);
        }
    }
    @PutMapping("/updateperson")
    public ResponseEntity updatePersonById(@RequestBody PersonDto personDto){
        Person person = personService.getByID(personDto.getPersonid());
        if(person == null){
            return ResponseEntity.badRequest().body("Podany użytkownik nie istnieje");
        }
        else{
            Person updated = personService.updatePerson(convertFromDto(personDto));
            return new ResponseEntity<>(convertToDto(updated), HttpStatus.OK);
        }
    }
    private PersonDto convertToDto(Person person){
        return modelMapper.map(person, PersonDto.class);
    }

    //TODO if entity contains list of other entities convertFromDTO needs to be updated
    private Person convertFromDto(PersonDto personDto){
        return modelMapper.map(personDto, Person.class);
    }
}
