package com.zpi.zpibackend.entity;

import com.zpi.zpibackend.entity.composite_key.EventPersonId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "eventperson")
public class EventPerson {

    @EmbeddedId
    private EventPersonId eventPersonId;
    @ManyToOne
    @JoinColumn(name = "roleid")
    private Role role;

    public EventPerson() {
    }

    public EventPerson(EventPersonId eventPersonId, Role role) {
        this.eventPersonId = eventPersonId;
        this.role = role;
    }

    public EventPerson(EventPersonId eventPersonId) {
        this.eventPersonId = eventPersonId;
    }

    public EventPersonId getEventPersonId() {
        return eventPersonId;
    }

    public void setEventPersonId(EventPersonId eventPersonId) {
        this.eventPersonId = eventPersonId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
