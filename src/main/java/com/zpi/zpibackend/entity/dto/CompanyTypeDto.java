package com.zpi.zpibackend.entity.dto;

public class CompanyTypeDto {


    private Integer companytypeid;
    private String type;

    public CompanyTypeDto() {
    }

    public Integer getCompanytypeid() {
        return companytypeid;
    }

    public void setCompanytypeid(Integer companytypeid) {
        this.companytypeid = companytypeid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
