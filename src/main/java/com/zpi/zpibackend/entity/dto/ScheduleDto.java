package com.zpi.zpibackend.entity.dto;

public class ScheduleDto {


    private Integer scheduleid;
    private EventDto event;
    private String title;

    public ScheduleDto() {
    }

    public Integer getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(Integer scheduleid) {
        this.scheduleid = scheduleid;
    }

    public EventDto getEvent() {
        return event;
    }

    public void setEvent(EventDto event) {
        this.event = event;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
