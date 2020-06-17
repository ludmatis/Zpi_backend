package com.zpi.zpibackend.entity.dto;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


public class EventDto {

    private Integer eventid;
    private String description;
    private String eventtype;
    private AddressDto address;
    private PersonDto creator;

    public EventDto() {
    }

    public Integer getEventid() {
        return eventid;
    }

    public void setEventid(Integer eventid) {
        this.eventid = eventid;
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

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public PersonDto getCreator() {
        return creator;
    }

    public void setCreator(PersonDto creator) {
        this.creator = creator;
    }
}
