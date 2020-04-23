package com.zpi.zpibackend.service;

import com.zpi.zpibackend.entity.Address;
import com.zpi.zpibackend.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address add(Address address){
        return addressRepository.save(address);
    }

    public List<Address> getAll(){
        return (List<Address>) addressRepository.findAll();
    }
}
