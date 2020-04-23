package com.zpi.zpibackend.entity;

import com.zpi.zpibackend.entity.composite_key.CompanyAddressId;

import javax.persistence.*;

@Entity(name = "companyaddress")
public class CompanyAddress {

    @EmbeddedId
    private CompanyAddressId companyAddressId;

    public CompanyAddress() {
    }

    public CompanyAddress(CompanyAddressId companyAddressId) {
        this.companyAddressId = companyAddressId;
    }

    public CompanyAddressId getCompanyAddressId() {
        return companyAddressId;
    }

    public void setCompanyAddressId(CompanyAddressId companyAddressId) {
        this.companyAddressId = companyAddressId;
    }

}
