package com.zpi.zpibackend.controller;

import com.zpi.zpibackend.entity.Event;
import com.zpi.zpibackend.entity.EventDetail;
import com.zpi.zpibackend.entity.dto.EventDetailDto;
import com.zpi.zpibackend.entity.dto.EventDto;
import com.zpi.zpibackend.service.EventDetailService;
import com.zpi.zpibackend.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eventdetail")
public class EventDetailController {

    @Autowired
    private EventDetailService eventDetailService;
    @Autowired
    private EventService eventService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public ResponseEntity getAll(){
        List<EventDetail> eventDetails = eventDetailService.getAll();
        if(eventDetails.isEmpty())
            return ResponseEntity.badRequest().body("Brak szczegółów wydarzeń?");
        else {
            List<EventDetailDto> eventDetailDtos= eventDetails.stream().map(this::convertToDto).collect(Collectors.toList());
            return new ResponseEntity<>(eventDetailDtos,HttpStatus.OK);
        }
    }
    @GetMapping("/get/{id}")
    public ResponseEntity getEventDetaulById(@PathVariable Integer id){
        EventDetail eventDetail = eventDetailService.getById(id);
        if(eventDetail==null)
            return ResponseEntity.badRequest().body("Event detail nie istnieje");
        else{
            return new ResponseEntity<>(convertToDto(eventDetail),HttpStatus.OK);
        }
    }
    @GetMapping("/getbyevent/{id}")
    public ResponseEntity getEventDetailsByEventId(@PathVariable Integer id){
        Event event = eventService.getById(id);
        if(event==null)
            return ResponseEntity.badRequest().body("Event nie istnieje");
        else {
            List<EventDetail> eventDetails = eventDetailService.getByEvent(event);
            if(eventDetails==null)
                return ResponseEntity.badRequest().body("Event nie ma detali ");
            List<EventDetailDto> eventDetailDtos = eventDetails.stream().map(this::convertToDto).collect(Collectors.toList());
            return new ResponseEntity<>(eventDetailDtos,HttpStatus.OK);

        }
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody EventDetailDto eventDetailDto) {
        EventDetail eventDetail = convertFromDto(eventDetailDto);
        if(eventDetailService.add(eventDetail) == null){
            return ResponseEntity.badRequest().body("Cos poszlo nie tak przy dodawaniu");
        }
        else {
            return new ResponseEntity<>(convertToDto(eventDetail), HttpStatus.OK);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateEvent(@RequestBody EventDetailDto eventDetailDto, @PathVariable Integer id){
        EventDetail eventDetail = eventDetailService.getById(id);
        eventDetailDto.setEventdetailid(id);
        if(eventDetail == null){
            return ResponseEntity.badRequest().body("Event detail nie istnieje");
        }
        else {
            EventDetail updated = eventDetailService.update(convertFromDto(eventDetailDto));
            return new ResponseEntity<>(convertToDto(updated), HttpStatus.OK);
        }
    }

    private EventDetailDto convertToDto(EventDetail eventDetail){
        return modelMapper.map(eventDetail, EventDetailDto.class);
    }


    private EventDetail convertFromDto(EventDetailDto eventDetailDto){
        EventDetail eventDetail = modelMapper.map(eventDetailDto, EventDetail.class);
        Event event;
        if(eventDetail.getEvent() != null){
            event=eventService.getById(eventDetail.getEvent().getEventid());
            eventDetail.setEvent(event);
        }
        return eventDetail;
    }
}
