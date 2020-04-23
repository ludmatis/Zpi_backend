package com.zpi.zpibackend.controller;

import com.zpi.zpibackend.entity.Schedule;
import com.zpi.zpibackend.entity.dto.ScheduleDto;
import com.zpi.zpibackend.service.ScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public List<ScheduleDto> getAll() {
        List<Schedule> schedules = scheduleService.getAll();
        return schedules.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private ScheduleDto convertToDto(Schedule schedule) {
        return modelMapper.map(schedule, ScheduleDto.class);
    }

    //TODO if entity constins list needs to be updated
    private Schedule convertFromDto(ScheduleDto scheduleDto) {
        return modelMapper.map(scheduleDto, Schedule.class);
    }
}
