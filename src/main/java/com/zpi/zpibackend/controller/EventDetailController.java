package com.zpi.zpibackend.controller;

import com.zpi.zpibackend.entity.EventDetail;
import com.zpi.zpibackend.entity.dto.EventDetailDto;
import com.zpi.zpibackend.service.EventDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eventdetail")
public class EventDetailController {

    @Autowired
    private EventDetailService eventDetailService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public List<EventDetailDto> getAll(){
        List<EventDetail> eventDetails = eventDetailService.getAll();
        return eventDetails.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private EventDetailDto convertToDto(EventDetail eventDetail){
        return modelMapper.map(eventDetail, EventDetailDto.class);
    }

    //TODO as every convertFromDto
    private EventDetail convertFromDto(EventDetailDto eventDetailDto){
        return modelMapper.map(eventDetailDto, EventDetail.class);
    }
}
