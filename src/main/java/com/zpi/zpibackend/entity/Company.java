package com.zpi.zpibackend.entity;

import javax.persistence.*;

@Entity(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer companyid;
    @ManyToOne
    @JoinColumn(name = "companytypeid")
    private CompanyType companyType;
    private String name;
    private String email;
    private String phonenumber;
    private String logourl;

    public Company() {
    }

    public Company(Integer companyid, CompanyType companyType, String name, String email, String phonenumber, String logourl) {
        this.companyid = companyid;
        this.companyType = companyType;
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
        this.logourl = logourl;
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public CompanyType getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getLogourl() {
        return logourl;
    }

    public void setLogourl(String logourl) {
        this.logourl = logourl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
