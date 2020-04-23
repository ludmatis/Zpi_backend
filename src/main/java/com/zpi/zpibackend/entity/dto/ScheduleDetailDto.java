package com.zpi.zpibackend.entity.dto;

import java.util.Date;

public class ScheduleDetailDto {


    private Integer scheduledetailid;
    private ScheduleDto schedule;
    private Date timestart;
    private Date timeend;
    private String description;


    public ScheduleDetailDto() {
    }

    public Integer getScheduledetailid() {
        return scheduledetailid;
    }

    public void setScheduledetailid(Integer scheduledetailid) {
        this.scheduledetailid = scheduledetailid;
    }

    public ScheduleDto getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleDto schedule) {
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
