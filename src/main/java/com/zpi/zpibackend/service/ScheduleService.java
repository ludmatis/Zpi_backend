package com.zpi.zpibackend.service;

import com.zpi.zpibackend.entity.Schedule;
import com.zpi.zpibackend.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public Schedule add(Schedule schedule){
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAll(){
        return (List<Schedule>) scheduleRepository.findAll();
    }
}