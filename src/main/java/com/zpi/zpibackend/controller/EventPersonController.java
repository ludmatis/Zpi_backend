package com.zpi.zpibackend.controller;

import com.zpi.zpibackend.entity.EventPerson;
import com.zpi.zpibackend.entity.dto.EventPersonDto;
import com.zpi.zpibackend.service.EventPersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eventperson")
public class EventPersonController {

    @Autowired
    private EventPersonService eventPersonService;
    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/all")
    public List<EventPersonDto> getAll(){
        List<EventPerson> eventPeople = eventPersonService.getAll();
        return eventPeople.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private EventPersonDto convertToDto(EventPerson eventPerson){
        return modelMapper.map(eventPerson, EventPersonDto.class);
    }
    private EventPerson convertFromDto(EventPersonDto eventPersonDto){
        return modelMapper.map(eventPersonDto, EventPerson.class);
    }
}
