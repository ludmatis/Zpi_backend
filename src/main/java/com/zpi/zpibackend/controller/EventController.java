package com.zpi.zpibackend.controller;

import com.zpi.zpibackend.entity.Address;
import com.zpi.zpibackend.entity.Event;
import com.zpi.zpibackend.entity.Person;
import com.zpi.zpibackend.entity.dto.EventDto;
import com.zpi.zpibackend.entity.dto.PersonDto;
import com.zpi.zpibackend.repository.EventRepository;
import com.zpi.zpibackend.service.AddressService;
import com.zpi.zpibackend.service.EventService;
import com.zpi.zpibackend.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private PersonService personService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public ResponseEntity getAll(){
        List<Event> events = eventService.getAll();
        if(events.isEmpty()){
            return ResponseEntity.badRequest().body("Brak wydarzen w bazie");
        }
        else{
            List<EventDto> eventDtos =  events.stream().map(this::convertToDto).collect(Collectors.toList());
            return new ResponseEntity<>(eventDtos, HttpStatus.OK);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getEventById(@PathVariable Integer id){
        Event event = eventService.getById(id);
        if(event == null){
            return ResponseEntity.badRequest().body("Event nie istnieje");
        }
        else {
            return new ResponseEntity<>(convertToDto(event), HttpStatus.OK);
        }
    }
    @GetMapping("/getbycreator/{id}")
    public ResponseEntity getEventsByCreatorid(@PathVariable Integer id){
        Person person = personService.getByID(id);
        if(person == null){
            return ResponseEntity.badRequest().body("Użytkownik nie istnieje");
        }
        else{
            List<Event> events = eventService.getByCreator(person);
            if(events == null){
                return ResponseEntity.badRequest().body("Użytkownik nie ma eventów");
            }
            List<EventDto> eventDtos = events.stream().map(this::convertToDto).collect(Collectors.toList());
            return new ResponseEntity<>(eventDtos, HttpStatus.OK);
        }
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody EventDto eventDto) {
        Event event = convertFromDto(eventDto);
        if(eventService.add(event) == null){
            return ResponseEntity.badRequest().body("Cos poszlo nie tak przy dodawaniu");
        }
        else {
            return new ResponseEntity<>(convertToDto(event), HttpStatus.OK);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateEvent(@RequestBody EventDto eventDto, @PathVariable Integer id){
        Event event = eventService.getById(id);
        eventDto.setEventid(id);
        if(event == null){
            return ResponseEntity.badRequest().body("Event nie istnieje");
        }
        else {
            Event updated = eventService.update(convertFromDto(eventDto));
            return new ResponseEntity<>(convertToDto(updated), HttpStatus.OK);
        }
    }


    private EventDto convertToDto(Event event){
        return modelMapper.map(event, EventDto.class);
    }
    private Event convertFromDto(EventDto eventDto){
        Event event = modelMapper.map(eventDto, Event.class);
        Person person;
        Address address;
        if(eventDto.getCreator() != null){
             person = personService.getByID(eventDto.getCreator().getPersonid());
             event.setCreator(person);
        }
        if(eventDto.getAddress() != null){
             address =  addressService.getById(eventDto.getAddress().getAddressid());
            event.setAddress(address);
        }
        return event;
    }
}
