package com.zpi.zpibackend.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "todolisttask")
public class ToDoListTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer taskid;
    @ManyToOne
    @JoinColumn(name = "todolistid")
    private ToDoList toDoList;
    @OneToMany(mappedBy = "parent")
    private List<ToDoListTask> toDoListTasks;
    @ManyToOne
    @JoinColumn(name = "parentid")
    private ToDoListTask parent;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "personid"),
            @JoinColumn(name = "eventid")
    } )
    private EventPerson executor;
    private String description;
    private Date timestart;
    private Date timeend;
    private String name;
    private Boolean done;
    private Integer priority;

    public ToDoListTask() {
    }

    public ToDoListTask(Integer taskid, ToDoList toDoList, EventPerson executor, String description, Date timestart, Date timeend) {
        this.taskid = taskid;
        this.toDoList = toDoList;
        this.executor = executor;
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

    public List<ToDoListTask> getToDoListTasks() {
        return toDoListTasks;
    }

    public void setToDoListTasks(List<ToDoListTask> toDoListTasks) {
        this.toDoListTasks = toDoListTasks;
    }

    public ToDoListTask getParent() {
        return parent;
    }

    public void setParent(ToDoListTask parent) {
        this.parent = parent;
    }

    public EventPerson getExecutor() {
        return executor;
    }

    public void setExecutor(EventPerson executor) {
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
