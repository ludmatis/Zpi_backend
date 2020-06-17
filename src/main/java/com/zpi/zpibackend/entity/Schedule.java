package com.zpi.zpibackend.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer scheduleid;
    @ManyToOne
    @JoinColumn(name = "eventid")
    private Event event;
    private String title;
    @OneToMany(mappedBy = "schedule")
    private List<ScheduleDetail> scheduleDetails;

    public Schedule() {
    }

    public Schedule(Integer scheduleid, Event event, String title) {
        this.scheduleid = scheduleid;
        this.event = event;
        this.title = title;
    }

    public Integer getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(Integer scheduleid) {
        this.scheduleid = scheduleid;
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

    public List<ScheduleDetail> getScheduleDetails(){
        return scheduleDetails;
    }

    public void setScheduleDetails(List<ScheduleDetail> scheduleDetails) {
        this.scheduleDetails = scheduleDetails;
    }
}
