package com.zpi.zpibackend.entity.dto;


public class AddressDto {


    private Integer addressid;
    private String city;
    private String street;
    private Integer number;
    private Integer subnumber;

    public AddressDto() {
    }

    public Integer getAddressid() {
        return addressid;
    }

    public void setAddressid(Integer addressid) {
        this.addressid = addressid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSubnumber() {
        return subnumber;
    }

    public void setSubnumber(Integer subnumber) {
        this.subnumber = subnumber;
    }

}
