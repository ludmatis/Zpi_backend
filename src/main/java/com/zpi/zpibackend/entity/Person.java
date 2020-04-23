package com.zpi.zpibackend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "person")
public class Person {

    @Id
    private Integer personid;
    private String email;
    private String password;
    private String phonenumber;

    @OneToMany(mappedBy = "creator")
    private List<Event> events;
    @OneToMany(mappedBy = "sender")
    private List<Message> messages_sent;
    @OneToMany(mappedBy = "receiver")
    private List<Message> messages_received;

    public Person() {
    }

    public Person(Integer personid, String email, String password, String phonenumber) {
        this.personid = personid;
        this.email = email;
        this.password = password;
        this.phonenumber = phonenumber;
    }


    public Integer getPersonid() {
        return personid;
    }

    public void setPersonid(Integer personid) {
        this.personid = personid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Message> getMessages_sent() {
        return messages_sent;
    }

    public void setMessages_sent(List<Message> messages_sent) {
        this.messages_sent = messages_sent;
    }

    public List<Message> getMessages_received() {
        return messages_received;
    }

    public void setMessages_received(List<Message> messages_received) {
        this.messages_received = messages_received;
    }
}
