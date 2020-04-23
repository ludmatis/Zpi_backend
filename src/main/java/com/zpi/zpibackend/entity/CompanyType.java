package com.zpi.zpibackend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "companytype")
public class CompanyType {

    @Id
    private Integer companytypeid;
    private String type;
    @OneToMany(mappedBy = "companyType")
    private List<Company> companies;

    public CompanyType() {
    }

    public CompanyType(Integer companytypeid, String type) {
        this.companytypeid = companytypeid;
        this.type = type;
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

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
}
