package com.zpi.zpibackend.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity(name = "todolist")
public class ToDoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer todolistid;
    @ManyToOne
    @JoinColumn(name = "eventid")
    private Event event;
    private String title;
    @OneToMany(mappedBy = "toDoList")
    List<ToDoListTask> toDoListTasks;
    public ToDoList() {
    }

    public ToDoList(Integer todolistid, Event event, String title) {
        this.todolistid = todolistid;
        this.event = event;
        this.title = title;
    }

    public Integer getTodolistid() {
        return todolistid;
    }

    public void setTodolistid(Integer todolistid) {
        this.todolistid = todolistid;
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

    public List<ToDoListTask> getToDoListTasks() {
        return toDoListTasks;
    }

    public void setToDoListTasks(List<ToDoListTask> toDoListTasks) {
        this.toDoListTasks = toDoListTasks;
    }
}
