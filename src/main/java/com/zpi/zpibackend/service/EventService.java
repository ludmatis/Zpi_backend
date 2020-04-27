package com.zpi.zpibackend.service;

import com.zpi.zpibackend.entity.Event;
import com.zpi.zpibackend.entity.Person;
import com.zpi.zpibackend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event add(Event event) {
        return eventRepository.save(event);
    }


    public List<Event> getAll() {
        return (List<Event>) eventRepository.findAll();
    }

    public Event getById(Integer id) {
        return eventRepository.findById(id).orElse(null);
    }

    public Event update(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getByCreator(Person person){
        return eventRepository.findByCreator(person);
    }

}
