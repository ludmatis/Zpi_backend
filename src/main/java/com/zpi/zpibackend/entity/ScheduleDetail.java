package com.zpi.zpibackend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity(name = "scheduledetail")
public class ScheduleDetail {

    @Id
    private Integer scheduledetailid;
    @ManyToOne
    @JoinColumn(name = "scheduleid")
    private Schedule schedule;
    private Date timestart;
    private Date timeend;
    private String description;

    public ScheduleDetail() {
    }

    public ScheduleDetail(Integer scheduledetailid, Schedule schedule, Date timestart, Date timeend, String description) {
        this.scheduledetailid = scheduledetailid;
        this.schedule = schedule;
        this.timestart = timestart;
        this.timeend = timeend;
        this.description = description;
    }

    public Integer getScheduledetailid() {
        return scheduledetailid;
    }

    public void setScheduledetailid(Integer scheduledetailid) {
        this.scheduledetailid = scheduledetailid;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Date getTimestart() {
        return timestart;
    }

    public void setTimestart(Date timestart) {
        this.timestart = timestart;
    }

    public Date getTimeend() {
        return timeend;
    }

    public void setTimeend(Date timeend) {
        this.timeend = timeend;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
