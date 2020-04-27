package com.zpi.zpibackend.service;

import com.zpi.zpibackend.entity.Event;
import com.zpi.zpibackend.entity.EventPerson;
import com.zpi.zpibackend.entity.composite_key.EventPersonId;
import com.zpi.zpibackend.repository.EventPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventPersonService {

    @Autowired
    private EventPersonRepository eventPersonRepository;

    public EventPerson add(EventPerson eventPerson){
        return  eventPersonRepository.save(eventPerson);
    }
    public List<EventPerson> getAll(){
        return (List<EventPerson>) eventPersonRepository.findAll();
    }

    public EventPerson getByEventId(Integer eventid){
        return eventPersonRepository.findById(eventid).orElse(null);
    }
}
