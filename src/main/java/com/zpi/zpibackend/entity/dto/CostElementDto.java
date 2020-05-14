package com.zpi.zpibackend.entity.dto;

public class CostElementDto {

    private Integer costelementid;
    private CostOrganizerDto costOrganizer;
    private CostElementDto parent;
    private Double cost;
    private String description;

    public CostElementDto() {
    }

    public Integer getCostelementid() {
        return costelementid;
    }

    public void setCostelementid(Integer costelementid) {
        this.costelementid = costelementid;
    }

    public CostOrganizerDto getCostOrganizer() {
        return costOrganizer;
    }

    public void setCostOrganizer(CostOrganizerDto costOrganizer) {
        this.costOrganizer = costOrganizer;
    }

    public CostElementDto getParent() {
        return parent;
    }

    public void setParent(CostElementDto parent) {
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
