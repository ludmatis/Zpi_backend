package com.zpi.zpibackend.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "costelement")
public class CostElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer costelementid;
    @ManyToOne
    @JoinColumn(name = "organizerid")
    private CostOrganizer costOrganizer;
    @OneToMany(mappedBy = "parent")
    private List<CostElement> costElements;
    @ManyToOne
    @JoinColumn(name = "parentid")
    private CostElement parent;
    private Double cost;
    private String description;

    public CostElement() {
    }

    public CostElement(Integer costelementid, CostOrganizer costOrganizer, Double cost, String description) {
        this.costelementid = costelementid;
        this.costOrganizer = costOrganizer;
        this.cost = cost;
        this.description = description;
    }

    public Integer getCostelementid() {
        return costelementid;
    }

    public void setCostelementid(Integer costelementid) {
        this.costelementid = costelementid;
    }

    public CostOrganizer getCostOrganizer() {
        return costOrganizer;
    }

    public void setCostOrganizer(CostOrganizer costOrganizer) {
        this.costOrganizer = costOrganizer;
    }

    public List<CostElement> getCostElements() {
        return costElements;
    }

    public void setCostElements(List<CostElement> costElements) {
        this.costElements = costElements;
    }

    public CostElement getParent() {
        return parent;
    }

    public void setParent(CostElement parent) {
        this.parent = parent;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
