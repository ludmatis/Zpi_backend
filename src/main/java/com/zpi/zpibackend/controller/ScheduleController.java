package com.zpi.zpibackend.controller;

import com.zpi.zpibackend.entity.Event;
import com.zpi.zpibackend.entity.Schedule;
import com.zpi.zpibackend.entity.ScheduleDetail;
import com.zpi.zpibackend.entity.dto.ScheduleDto;
import com.zpi.zpibackend.service.EventService;
import com.zpi.zpibackend.service.ScheduleDetailService;
import com.zpi.zpibackend.service.ScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private ScheduleDetailService scheduleDetailService;
    @Autowired
    private EventService eventService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public List<ScheduleDto> getAll() {
        List<Schedule> schedules = scheduleService.getAll();
        return schedules.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/getbyeventid/{id}")
    public ResponseEntity getSchedulesByEvent(@PathVariable Integer id){
        Event event = eventService.getById(id);
        if(event == null){
            return ResponseEntity.badRequest().body("Event nie istnieje");
        }
        else{
            List<Schedule> schedules = scheduleService.getByEvent(event);
            if(schedules.isEmpty()){
                return ResponseEntity.badRequest().body("Event nie ma harmonogramow");
            }
            else{
                List<ScheduleDto> scheduleDtos = schedules.stream().map(this::convertToDto).collect(Collectors.toList());
                return new ResponseEntity<>(scheduleDtos, HttpStatus.OK);
            }

        }
    }

    @PostMapping("/add")
    public ResponseEntity addSchedule(@RequestBody ScheduleDto scheduleDto){
        Schedule schedule = convertFromDto(scheduleDto);
        if(scheduleService.add(schedule) == null){
            return ResponseEntity.badRequest().body("cos poszlo nie tak przy dodawaniu");
        }
        else return new ResponseEntity<>(convertToDto(schedule), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateSchedule(@RequestBody ScheduleDto scheduleDto, @PathVariable Integer id){
        Schedule schedule = scheduleService.getById(id);
        scheduleDto.setScheduleid(id);
        if(schedule == null){
            return ResponseEntity.badRequest().body("Podany harmonogram nie istnieje");
        }
        else {
            Schedule updated = scheduleService.update(convertFromDto(scheduleDto));
            return new ResponseEntity<>(convertToDto(updated), HttpStatus.OK);
        }
    }
    private ScheduleDto convertToDto(Schedule schedule) {
        return modelMapper.map(schedule, ScheduleDto.class);
    }


    private Schedule convertFromDto(ScheduleDto scheduleDto) {
        Schedule schedule = modelMapper.map(scheduleDto, Schedule.class);
        Event event;
        if(scheduleDto.getEvent() !=null){
            event = eventService.getById(scheduleDto.getEvent().getEventid());
            schedule.setEvent(event);
        }

        Integer scheduleId = scheduleDto.getScheduleid();
        List<ScheduleDetail> allScheduleDetails = scheduleDetailService.getAll();
        List<ScheduleDetail> exactScheduleDetails = new ArrayList<>();
        allScheduleDetails.forEach(scheduleDetail -> {
            if(scheduleDetail.getSchedule().getScheduleid()== scheduleId)
                exactScheduleDetails.add(scheduleDetail);
        });
        schedule.setScheduleDetails(exactScheduleDetails);
        return schedule;
    }
}
