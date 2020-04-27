package com.zpi.zpibackend.service;

import com.zpi.zpibackend.entity.CompanyAddress;
import com.zpi.zpibackend.repository.CompanyAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyAddressService {

    @Autowired
    private CompanyAddressRepository companyAddressRepository;

    public CompanyAddress add(CompanyAddress companyAddress){
        return  companyAddressRepository.save(companyAddress);
    }

    public List<CompanyAddress> getAll(){
        return (List<CompanyAddress>) companyAddressRepository.findAll();
    }

    public CompanyAddress getById(Integer id){
        return companyAddressRepository.findById(id).orElse(null);
    }
    public CompanyAddress update(CompanyAddress companyAddress){
        return  companyAddressRepository.save(companyAddress);
    }

}
