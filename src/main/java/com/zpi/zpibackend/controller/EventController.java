package com.zpi.zpibackend.controller;

import com.zpi.zpibackend.entity.Event;
import com.zpi.zpibackend.entity.Person;
import com.zpi.zpibackend.entity.dto.EventDto;
import com.zpi.zpibackend.repository.EventRepository;
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
    private EventRepository eventRepository;

    @Autowired
    private EventService eventService;
    @Autowired
    private PersonService personService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public List<EventDto> getAll(){
        List<Event> events = eventService.getAll();
        return events.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    @PostMapping("/event")
    @ResponseBody
    Event addEntity(@RequestBody EventDto newEventDto) {
        return eventService.add(modelMapper.map(newEventDto, Event.class));
    }


    private EventDto convertToDto(Event event){
        return modelMapper.map(event, EventDto.class);
    }
    private Event convertFromDto(EventDto eventDto){
        Person person= personService.getByID(eventDto.getCreator().getPersonid());
        List<Event> evList =  person.getEvents();
        Event event = modelMapper.map(eventDto, Event.class);
        evList.add(event);
        person.setEvents(evList);
        return event;
    }
}
