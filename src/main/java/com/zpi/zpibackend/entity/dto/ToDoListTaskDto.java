package com.zpi.zpibackend.entity.dto;

import com.zpi.zpibackend.entity.ToDoList;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

public class ToDoListTaskDto {


    private Integer taskid;
    private ToDoListDto toDoList;
    private String description;
    private Date timestart;
    private Date timeend;

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
}
