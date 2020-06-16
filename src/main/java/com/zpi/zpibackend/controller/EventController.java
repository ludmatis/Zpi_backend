package com.zpi.zpibackend.controller;

import com.zpi.zpibackend.entity.*;
import com.zpi.zpibackend.entity.dto.EventDto;
import com.zpi.zpibackend.entity.dto.PersonDto;
import com.zpi.zpibackend.repository.EventRepository;
import com.zpi.zpibackend.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin()
@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private ToDoListService toDoListService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private EventDetailService eventDetailService;
    @Autowired
    private CostOrganizerService costOrganizerService;
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
    /*@GetMapping("/get/creatorid/{id}")
    public ResponseEntity getByCreatorID(@PathVariable Integer id){
        List<Event> events = eventService.getByCreatorID(id);
        List<EventDto> eventDtos =  events.stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(eventDtos, HttpStatus.OK);
    }*/
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

        Integer eventId = eventDto.getEventid();
        List<ToDoList> allToDoLists = toDoListService.getAll();
        List<ToDoList> exactToDoLists = new ArrayList<>();
        allToDoLists.forEach(toDoList -> {
            if(toDoList.getEvent().getEventid()==eventId)
                exactToDoLists.add(toDoList);
        });
        List<Schedule> allSchedules = scheduleService.getAll();
        List<Schedule> exactSchedules = new ArrayList<>();
        allSchedules.forEach(schedule -> {
            if(schedule.getEvent().getEventid()==eventId)
                exactSchedules.add(schedule);
        });
        List<EventDetail> allEventDetails = eventDetailService.getAll();
        List<EventDetail> exactEventDetails = new ArrayList<>();
        allEventDetails.forEach(eventDetail -> {
            if(eventDetail.getEvent().getEventid()==eventId)
                exactEventDetails.add(eventDetail);
        });
        List<CostOrganizer> allCostOrganizers = costOrganizerService.getAll();
        List<CostOrganizer> exactCostOrganizers = new ArrayList<>();
        allCostOrganizers.forEach(costOrganizer -> {
            if(costOrganizer.getEvent().getEventid()==eventId)
                exactCostOrganizers.add(costOrganizer);
        });

        event.setToDoLists(exactToDoLists);
        event.setSchedules(exactSchedules);
        event.setEventDetails(exactEventDetails);
        event.setCostOrganizers(exactCostOrganizers);

        return event;
    }


}
