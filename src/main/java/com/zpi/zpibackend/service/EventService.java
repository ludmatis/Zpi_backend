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
    @Autowired
    private EventPersonService eventPersonService;
    @Autowired
    private ToDoListService toDoListService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private CostOrganizerService costOrganizerService;

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

    public void delete(Event event){
        var toDoLists = toDoListService.geByEvent(event);
        toDoLists.forEach(toDoList -> toDoListService.delete(toDoList));
        var eventPeople = eventPersonService.getAll();
        var properEventPersons = eventPeople.stream().filter(x -> x.getEventPersonId().getEventid() == event.getEventid());
        properEventPersons.forEach(eventPerson -> eventPersonService.delete(eventPerson));
        var costOrganizers = costOrganizerService.getByEvent(event);
        costOrganizers.forEach(costOrganizer -> costOrganizerService.delete(costOrganizer));
        var schedules = scheduleService.getByEvent(event);
        schedules.forEach(schedule -> scheduleService.delete(schedule));
        eventRepository.delete(event);
    }

}
