package com.zpi.zpibackend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity(name = "todolisttask")
public class ToDoListTask {

    @Id
    private Integer taskid;
    @ManyToOne
    @JoinColumn(name = "todolistid")
    private ToDoList toDoList;
    private String description;
    private Date timestart;
    private Date timeend;

    public ToDoListTask() {
    }

    public ToDoListTask(Integer taskid, ToDoList toDoList, String description, Date timestart, Date timeend) {
        this.taskid = taskid;
        this.toDoList = toDoList;
        this.description = description;
        this.timestart = timestart;
        this.timeend = timeend;
    }

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    public ToDoList getToDoList() {
        return toDoList;
    }

    public void setToDoList(ToDoList toDoList) {
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
