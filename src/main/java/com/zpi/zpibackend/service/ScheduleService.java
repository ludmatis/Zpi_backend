package com.zpi.zpibackend.service;

import com.zpi.zpibackend.entity.Event;
import com.zpi.zpibackend.entity.Schedule;
import com.zpi.zpibackend.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired ScheduleDetailService scheduleDetailService;

    public Schedule add(Schedule schedule){
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAll(){
        return (List<Schedule>) scheduleRepository.findAll();
    }

    public Schedule getById(Integer id){
        return scheduleRepository.findById(id).orElse(null);
    }
    public Schedule update(Schedule schedule){
        return scheduleRepository.save(schedule);
    }
    public List<Schedule> getByEvent(Event event){
        return scheduleRepository.findByEvent(event);
    }
    public void delete(Schedule schedule){
        var scheduleDetails = schedule.getScheduleDetails();
        scheduleDetails.forEach(scheduleDetail -> scheduleDetailService.delete(scheduleDetail));
        scheduleRepository.delete(schedule);
    }
}
