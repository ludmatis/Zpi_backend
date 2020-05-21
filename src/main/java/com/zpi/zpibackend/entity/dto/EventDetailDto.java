package com.zpi.zpibackend.entity.dto;

public class EventDetailDto {

    private Integer eventdetailid;
    private EventDto event;
    private String type;
    private String value;
    private byte[] image;

    public EventDetailDto() {
    }

    public Integer getEventdetailid() {
        return eventdetailid;
    }

    public void setEventdetailid(Integer eventdetailid) {
        this.eventdetailid = eventdetailid;
    }

    public EventDto getEvent() {
        return event;
    }

    public void setEvent(EventDto event) {
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
