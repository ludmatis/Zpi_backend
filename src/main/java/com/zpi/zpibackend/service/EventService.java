package com.zpi.zpibackend.service;

import com.zpi.zpibackend.entity.Event;
import com.zpi.zpibackend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event add(Event event){
        return eventRepository.save(event);
    }

    public Event get(Integer id){
        return eventRepository.findById(id).get();
    }

    public List<Event> getAll(){
        return (List<Event>) eventRepository.findAll();
    }

}
