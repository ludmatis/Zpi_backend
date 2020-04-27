package com.zpi.zpibackend.service;

import com.zpi.zpibackend.entity.EventDetail;
import com.zpi.zpibackend.repository.EventDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventDetailService {

    @Autowired
    private EventDetailRepository eventDetailRepository;

    public EventDetail add(EventDetail eventDetail){
        return eventDetailRepository.save(eventDetail);
    }

    public List<EventDetail> getAll(){
        return (List<EventDetail>) eventDetailRepository.findAll();
    }

    public EventDetail getById(Integer id){
        return eventDetailRepository.findById(id).orElse(null);
    }

    public EventDetail update(EventDetail eventDetail){
        return eventDetailRepository.save(eventDetail);
    }


}
