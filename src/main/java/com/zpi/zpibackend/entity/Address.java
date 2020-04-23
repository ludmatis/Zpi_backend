package com.zpi.zpibackend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "address")
public class Address {

    @Id
    private Integer addressid;
    private String city;
    private String street;
    private Integer number;
    private Integer subnumber;
    @OneToMany(mappedBy = "address")
    private List<Event> events;

    public Address() {
    }

    public Address(Integer addressid, String city, String street, Integer number, Integer subnumber) {
        this.addressid = addressid;
        this.city = city;
        this.street = street;
        this.number = number;
        this.subnumber = subnumber;
    }

    public Address(Integer addressid) {
        this.addressid = addressid;
    }

    public Integer getAddressid() {
        return addressid;
    }

    public void setAddressid(Integer addressid) {
        this.addressid = addressid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSubnumber() {
        return subnumber;
    }

    public void setSubnumber(Integer subnumber) {
        this.subnumber = subnumber;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
