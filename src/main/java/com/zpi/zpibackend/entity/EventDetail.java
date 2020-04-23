package com.zpi.zpibackend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "eventdetail")
public class EventDetail {

    @Id
    private Integer eventdetailid;
    @ManyToOne
    @JoinColumn(name = "eventid")
    private Event event;
    private String type;
    private String value;

    public EventDetail() {
    }

    public EventDetail(Integer eventdetailid, Event event, String type, String value) {
        this.eventdetailid = eventdetailid;
        this.event = event;
        this.type = type;
        this.value = value;
    }

    public Integer getEventdetailid() {
        return eventdetailid;
    }

    public void setEventdetailid(Integer eventdetailid) {
        this.eventdetailid = eventdetailid;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
