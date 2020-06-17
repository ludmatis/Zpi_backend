package com.zpi.zpibackend.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "costorganizer")
public class CostOrganizer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer organizerid;
    @ManyToOne
    @JoinColumn(name = "eventid")
    private Event event;
    private String title;
    private Date createon;
    @OneToMany(mappedBy = "costOrganizer")
    private List<CostElement> costElements;


    public CostOrganizer() {
    }

    public CostOrganizer(Integer organizerid, Event event, String title, Date createon) {
        this.organizerid = organizerid;
        this.event = event;
        this.title = title;
        this.createon = createon;
    }

    public Integer getOrganizerid() {
        return organizerid;
    }

    public void setOrganizerid(Integer organizerid) {
        this.organizerid = organizerid;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateon() {
        return createon;
    }

    public void setCreateon(Date createon) {
        this.createon = createon;
    }

    public List<CostElement> getCostElements() {
        return costElements;
    }

    public void setCostElements(List<CostElement> costElements) {
        this.costElements = costElements;
    }
}
