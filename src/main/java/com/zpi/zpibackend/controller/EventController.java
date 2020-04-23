package com.zpi.zpibackend.controller;

import com.zpi.zpibackend.entity.Event;
import com.zpi.zpibackend.entity.dto.EventDto;
import com.zpi.zpibackend.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public List<EventDto> getAll(){
        List<Event> events = eventService.getAll();
        return events.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private EventDto convertToDto(Event event){
        return modelMapper.map(event, EventDto.class);
    }
    private Event convertFromDto(EventDto eventDto){
        return modelMapper.map(eventDto, Event.class);
    }
}
