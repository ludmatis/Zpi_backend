package com.zpi.zpibackend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity(name = "costorganizer")
public class CostOrganizer {

    @Id
    private Integer organizerid;
    @ManyToOne
    @JoinColumn(name = "eventid")
    private Event event;
    private String title;
    private Date createon;

    public CostOrganizer() {
    }

    public CostOrganizer(Integer organizerid, Event event, String title, Date createon) {
        this.organizerid = organizerid;
        this.event = event;
        this.title = title;
        this.createon = createon;
    }

    public Integer getOrganizerid() {
        return organizerid;
    }

    public void setOrganizerid(Integer organizerid) {
        this.organizerid = organizerid;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateon() {
        return createon;
    }

    public void setCreateon(Date createon) {
        this.createon = createon;
    }
}
