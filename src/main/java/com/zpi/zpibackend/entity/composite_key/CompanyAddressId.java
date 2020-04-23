package com.zpi.zpibackend.entity.composite_key;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CompanyAddressId implements Serializable {

    private Integer companyid;
    private Integer addressid;

    public CompanyAddressId() {
    }

    public CompanyAddressId(Integer companyid, Integer addressid) {
        this.companyid = companyid;
        this.addressid = addressid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyAddressId that = (CompanyAddressId) o;
        return Objects.equals(companyid, that.companyid) &&
                Objects.equals(addressid, that.addressid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyid, addressid);
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public Integer getAddressid() {
        return addressid;
    }

    public void setAddressid(Integer addressid) {
        this.addressid = addressid;
    }
}
