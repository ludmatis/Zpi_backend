package com.zpi.zpibackend.entity.dto;

public class ToDoListDto {


    private Integer todolistid;
    private EventDto event;
    private String title;

    public ToDoListDto() {
    }

    public Integer getTodolistid() {
        return todolistid;
    }

    public void setTodolistid(Integer todolistid) {
        this.todolistid = todolistid;
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
}
