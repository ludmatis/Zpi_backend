package com.zpi.zpibackend.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer eventid;
    @ManyToOne
    @JoinColumn(name = "creatorid")
    private Person creator;
    @ManyToOne
    @JoinColumn(name = "addressid")
    private Address address;
    private String description;
    private String eventtype;

    @OneToMany(mappedBy = "event")
    private List<ToDoList> toDoLists;
    @OneToMany(mappedBy = "event")
    private List<Schedule> schedules;
    @OneToMany(mappedBy = "event")
    private List<EventDetail> eventDetails;
    @OneToMany(mappedBy = "event")
    private List<CostOrganizer>costOrganizers;



    public Event() {
    }

    public Event(Integer eventid, Person creator, Address address, String description, String eventtype) {
        this.eventid = eventid;
        this.creator = creator;
        this.address = address;
        this.description = description;
        this.eventtype = eventtype;
    }

    public Integer getEventid() {
        return eventid;
    }

    public void setEventid(Integer eventid) {
        this.eventid = eventid;
    }

    public Person getCreator() {
        return creator;
    }

    public void setCreator(Person creator) {
        this.creator = creator;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventtype() {
        return eventtype;
    }

    public void setEventtype(String eventtype) {
        this.eventtype = eventtype;
    }

    public List<ToDoList> getToDoLists() {
        return toDoLists;
    }

    public void setToDoLists(List<ToDoList> toDoLists) {
        this.toDoLists = toDoLists;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public List<EventDetail> getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(List<EventDetail> eventDetails){
        this.eventDetails=eventDetails;
    }

    public List<CostOrganizer> getCostOrganizers() {
        return costOrganizers;
    }

    public void setCostOrganizers(List<CostOrganizer> costOrganizers){
        this.costOrganizers = costOrganizers;
    }


}
