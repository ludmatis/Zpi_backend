package com.zpi.zpibackend.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.zpi.zpibackend.entity.Event;
import com.zpi.zpibackend.entity.Message;
import com.zpi.zpibackend.entity.Person;
import com.zpi.zpibackend.entity.dto.PersonDto;
import com.zpi.zpibackend.service.EventService;
import com.zpi.zpibackend.service.MessageService;
import com.zpi.zpibackend.service.PersonService;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;
    @Autowired
    private EventService eventService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/get")
    public List<PersonDto> getAll(){
        List<Person> people = personService.getAll();
        return people.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getPersonById(@PathVariable Integer id){
        Person person = personService.getByID(id);
        if(person == null){
            return ResponseEntity.badRequest().body("Użytkownik nie istnieje");
        }
        else return new ResponseEntity<>(convertToDto(person),HttpStatus.OK);
    }

    @GetMapping("/get/{email}/{password}")
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
    @PostMapping("/add")
    public ResponseEntity addPerson(@RequestBody PersonDto personDto){
        Person person = convertFromDto(personDto);
        if(personService.getByEmail(person.getEmail()) != null){
            return ResponseEntity.badRequest().body("Podany email jest zajęty");
        }
        else{
            personService.add(person);
            return new ResponseEntity<>(convertToDto(person), HttpStatus.OK);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updatePersonById(@RequestBody PersonDto personDto, @PathVariable Integer id){
        Person person = personService.getByID(id);
        personDto.setPersonid(id);
        if(person == null){
            return ResponseEntity.badRequest().body("Podany użytkownik nie istnieje");
        }
        else{
            personService.updatePerson(convertFromDto(personDto));
            return new ResponseEntity<>(personDto, HttpStatus.OK);
        }
    }
    private PersonDto convertToDto(Person person){
        return modelMapper.map(person, PersonDto.class);
    }

    //TODO if entity contains list of other entities convertFromDTO needs to be updated
    private Person convertFromDto(PersonDto personDto){
        Person person = modelMapper.map(personDto, Person.class);

        List<Event> allEvents = eventService.getAll();
        List<Event> exactEvents = new ArrayList<>();
        allEvents.forEach(event -> {
            if(event.getCreator().getPersonid()==personDto.getPersonid())
                exactEvents.add(event);
        });
        person.setEvents(allEvents);

        List<Message> allMessages = messageService.getAll();
        List<Message> receivedMessages = new ArrayList<>();
        List<Message> sentMessages = new ArrayList<>();
        allMessages.forEach(message -> {
            if(message.getReceiver().getPersonid()==personDto.getPersonid())
                receivedMessages.add(message);
            if(message.getSender().getPersonid()==personDto.getPersonid())
                sentMessages.add(message);
        });
        person.setMessages_received(receivedMessages);
        person.setMessages_sent(sentMessages);
        return person;
    }
}
