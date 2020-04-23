package com.zpi.zpibackend.entity.dto;

import com.zpi.zpibackend.entity.Role;
import com.zpi.zpibackend.entity.composite_key.EventPersonId;



public class EventPersonDto {

    private EventPersonId eventPersonId;
    private RoleDto role;

    public EventPersonDto() {
    }

    public EventPersonId getEventPersonId() {
        return eventPersonId;
    }

    public void setEventPersonId(EventPersonId eventPersonId) {
        this.eventPersonId = eventPersonId;
    }

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }
}
