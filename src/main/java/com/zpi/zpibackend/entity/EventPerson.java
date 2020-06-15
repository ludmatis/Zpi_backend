package com.zpi.zpibackend.entity;

import com.zpi.zpibackend.entity.composite_key.EventPersonId;

import javax.persistence.*;
import java.util.List;

@Entity(name = "eventperson")
public class EventPerson {

    @EmbeddedId
    private EventPersonId eventPersonId;
    @ManyToOne
    @JoinColumn(name = "roleid")
    private Role role;
    @OneToMany(mappedBy = "executor")
    private List<ToDoListTask> toDoListTasks;

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

    public List<ToDoListTask> getToDoListTasks() {
        return toDoListTasks;
    }

    public void setToDoListTasks(List<ToDoListTask> toDoListTasks) {
        this.toDoListTasks = toDoListTasks;
    }
}
