package com.zpi.zpibackend.entity.dto;

public class CompanyDto {

    private Integer companyid;
    private CompanyTypeDto companyType;
    private String name;
    private String email;
    private String phonenumber;
    private String logourl;

    public CompanyDto() {
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public CompanyTypeDto getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyTypeDto companyType) {
        this.companyType = companyType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
