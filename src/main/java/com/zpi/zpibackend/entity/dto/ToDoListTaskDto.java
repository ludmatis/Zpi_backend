package com.zpi.zpibackend.entity.dto;

import com.zpi.zpibackend.entity.ToDoList;
import com.zpi.zpibackend.entity.ToDoListTask;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

public class ToDoListTaskDto {


    private Integer taskid;
    private ToDoListDto toDoList;
    private ToDoListTaskDto parent;
    private EventPersonDto executor;
    private String description;
    private Date timestart;
    private Date timeend;
    private String name;
    private Boolean done;
    private Integer priority;

    public ToDoListTaskDto() {
    }

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    public ToDoListDto getToDoList() {
        return toDoList;
    }

    public void setToDoList(ToDoListDto toDoList) {
        this.toDoList = toDoList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public ToDoListTaskDto getParent() {
        return parent;
    }

    public void setParent(ToDoListTaskDto parent) {
        this.parent = parent;
    }

    public EventPersonDto getExecutor() {
        return executor;
    }

    public void setExecutor(EventPersonDto executor) {
        this.executor = executor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
