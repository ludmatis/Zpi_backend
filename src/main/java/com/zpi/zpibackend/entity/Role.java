package com.zpi.zpibackend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Role {

    @Id
    private Integer roleid;
    private String rolename;
    @OneToMany(mappedBy = "role")
    private List<EventPerson> eventPeople;

    public Role() {
    }

    public Role(Integer roleid, String rolename) {
        this.roleid = roleid;
        this.rolename = rolename;
    }

    public Role(String roleName) {
        this.rolename = roleName;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public List<EventPerson> getEventPeople() {
        return eventPeople;
    }

    public void setEventPeople(List<EventPerson> eventPeople) {
        this.eventPeople = eventPeople;
    }
}
