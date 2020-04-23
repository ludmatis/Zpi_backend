package com.zpi.zpibackend.entity.dto;

import java.util.Date;

public class CostOrganizerDto {

    private Integer organizerid;
    private EventDto event;
    private String title;
    private Date createon;

    public CostOrganizerDto() {
    }

    public Integer getOrganizerid() {
        return organizerid;
    }

    public void setOrganizerid(Integer organizerid) {
        this.organizerid = organizerid;
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

    public Date getCreateon() {
        return createon;
    }

    public void setCreateon(Date createon) {
        this.createon = createon;
    }
}
