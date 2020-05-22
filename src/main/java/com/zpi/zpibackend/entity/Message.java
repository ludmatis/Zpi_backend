package com.zpi.zpibackend.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer messageid;
    @ManyToOne
    @JoinColumn(name = "senderid")
    private Person sender;
    @ManyToOne
    @JoinColumn(name = "receiverid")
    private Person receiver;
    private String subject;
    private String body;
    private Date senton;

    public Message() {
    }

    public Message(Integer messageid, Person sender, Person receiver, String subject, String body, Date senton) {
        this.messageid = messageid;
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.body = body;
        this.senton = senton;
    }

    public Integer getMessageid() {
        return messageid;
    }

    public void setMessageid(Integer messageid) {
        this.messageid = messageid;
    }

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public Person getReceiver() {
        return receiver;
    }

    public void setReceiver(Person receiver) {
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
