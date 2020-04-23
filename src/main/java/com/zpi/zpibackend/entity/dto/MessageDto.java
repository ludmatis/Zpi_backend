package com.zpi.zpibackend.entity.dto;

import java.util.Date;

public class MessageDto {

    private Integer messageid;
    private PersonDto sender;
    private PersonDto receiver;
    private String subject;
    private String body;
    private Date senton;

    public MessageDto() {
    }

    public Integer getMessageid() {
        return messageid;
    }

    public void setMessageid(Integer messageid) {
        this.messageid = messageid;
    }

    public PersonDto getSender() {
        return sender;
    }

    public void setSender(PersonDto sender) {
        this.sender = sender;
    }

    public PersonDto getReceiver() {
        return receiver;
    }

    public void setReceiver(PersonDto receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getSenton() {
        return senton;
    }

    public void setSenton(Date senton) {
        this.senton = senton;
    }
}
