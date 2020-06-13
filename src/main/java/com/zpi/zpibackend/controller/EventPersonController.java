package com.zpi.zpibackend.controller;

import com.zpi.zpibackend.entity.EventPerson;
import com.zpi.zpibackend.entity.Role;
import com.zpi.zpibackend.entity.composite_key.EventPersonId;
import com.zpi.zpibackend.entity.dto.EventPersonDto;
import com.zpi.zpibackend.service.EventPersonService;
import com.zpi.zpibackend.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eventperson")
public class EventPersonController {

    @Autowired
    private EventPersonService eventPersonService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/all")
    public ResponseEntity getAll(){
        List<EventPerson> eventPeople = eventPersonService.getAll();
        if(eventPeople.isEmpty())
            return  ResponseEntity.badRequest().body("Brak eventpeople w bazie");
        else{
           List<EventPersonDto> eventPersonDtos = eventPeople.stream().map(this::convertToDto).collect(Collectors.toList());
           return new ResponseEntity<>(eventPersonDtos, HttpStatus.OK);
        }
    }

    @GetMapping("/get/{personid}/{eventid}")
    public ResponseEntity getById(@PathVariable(value = "personid") Integer personId, @PathVariable(value = "eventid") Integer eventId){
        EventPersonId eventPersonId = new EventPersonId(personId,eventId);
        EventPerson eventPerson = eventPersonService.getById(eventPersonId);
        if(eventPerson == null){
            return ResponseEntity.badRequest().body("Event person nieistnieje");
        }
        else
            return new ResponseEntity<>(convertToDto(eventPerson),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity add (@RequestBody EventPersonDto eventPersonDto){
        EventPerson eventPerson = convertFromDto(eventPersonDto);
        if(eventPersonService.add(eventPerson)==null)
            return ResponseEntity.badRequest().body("Blad przy dodawaniu");
        else
            return new ResponseEntity<>(convertToDto(eventPerson),HttpStatus.OK);
    }

    @PutMapping("/update/{personid}/{eventid}")
    public ResponseEntity updateEventPerson(@RequestBody EventPersonDto eventPersonDto,
                                            @PathVariable(value = "personid") Integer personId,
                                            @PathVariable(value = "eventid") Integer eventId){
        EventPersonId eventPersonId = new EventPersonId(personId,eventId);
        EventPerson eventPerson = eventPersonService.getById(eventPersonId);
        eventPersonDto.setEventPersonId(eventPersonId);
        if(eventPerson == null){
            return ResponseEntity.badRequest().body("Event person nieistnieje");
        }
        else{
            EventPerson updated = eventPersonService.update(convertFromDto(eventPersonDto));
            return new ResponseEntity<>(convertToDto(updated),HttpStatus.OK);
        }
    }
    @DeleteMapping("delete/{personid}/{eventid}")
    public ResponseEntity deleteEventPerson(@PathVariable(value = "personid") Integer personId, @PathVariable(value = "eventid") Integer eventId){
        EventPersonId eventPersonId = new EventPersonId(personId,eventId);
        EventPerson eventPerson = eventPersonService.getById(eventPersonId);
        if(eventPerson == null){
            return ResponseEntity.badRequest().body("Event person nieistnieje");
        }
        else{
            eventPersonService.delete(eventPerson);
            return ResponseEntity.ok().body("Event person usuniete");
        }
    }

    private EventPersonDto convertToDto(EventPerson eventPerson){
        return modelMapper.map(eventPerson, EventPersonDto.class);
    }
    private EventPerson convertFromDto(EventPersonDto eventPersonDto){
        EventPerson eventPerson = modelMapper.map(eventPersonDto, EventPerson.class);

        Role role;
        if(eventPersonDto.getRole() != null){
            role = roleService.getById(eventPersonDto.getRole().getRoleid());
            eventPerson.setRole(role);
        }
        return eventPerson;
    }
}
