package com.zpi.zpibackend.entity.dto;

import com.zpi.zpibackend.entity.composite_key.CompanyAddressId;

public class CompanyAddressDto {

    private CompanyAddressId companyAddressId;

    public CompanyAddressDto() {
    }

    public CompanyAddressId getCompanyAddressId() {
        return companyAddressId;
    }

    public void setCompanyAddressId(CompanyAddressId companyAddressId) {
        this.companyAddressId = companyAddressId;
    }
}
