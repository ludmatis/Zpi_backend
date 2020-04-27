package com.zpi.zpibackend.service;

import com.zpi.zpibackend.entity.Schedule;
import com.zpi.zpibackend.entity.ScheduleDetail;
import com.zpi.zpibackend.repository.ScheduleDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleDetailService {

    @Autowired
    private ScheduleDetailRepository scheduleDetailRepository;

    public ScheduleDetail add(ScheduleDetail scheduleDetail){
        return  scheduleDetailRepository.save(scheduleDetail);
    }

    public List<ScheduleDetail> getAll(){
        return (List<ScheduleDetail>) scheduleDetailRepository.findAll();
    }

    public ScheduleDetail getById(Integer id){
        return scheduleDetailRepository.findById(id).orElse(null);
    }

    public ScheduleDetail update(ScheduleDetail scheduleDetail){
        return  scheduleDetailRepository.save(scheduleDetail);
    }

}
