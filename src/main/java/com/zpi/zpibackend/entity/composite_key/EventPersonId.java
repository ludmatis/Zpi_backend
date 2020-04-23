package com.zpi.zpibackend.entity.composite_key;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EventPersonId implements Serializable {

    private Integer eventid;
    private Integer personid;

    public EventPersonId() {
    }

    public EventPersonId(Integer eventid, Integer personid) {
        this.eventid = eventid;
        this.personid = personid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventPersonId that = (EventPersonId) o;
        return Objects.equals(eventid, that.eventid) &&
                Objects.equals(personid, that.personid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventid, personid);
    }

    public Integer getEventid() {
        return eventid;
    }

    public void setEventid(Integer eventid) {
        this.eventid = eventid;
    }

    public Integer getPersonid() {
        return personid;
    }

    public void setPersonid(Integer personid) {
        this.personid = personid;
    }
}
