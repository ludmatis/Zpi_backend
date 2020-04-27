package com.zpi.zpibackend.service;

import com.zpi.zpibackend.entity.CompanyType;
import com.zpi.zpibackend.repository.CompanyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyTypeService {

    @Autowired
    private CompanyTypeRepository companyTypeRepository;

    public CompanyType add(CompanyType companyType){
        return companyTypeRepository.save(companyType);
    }

    public List<CompanyType> getAll(){
        return (List<CompanyType>) companyTypeRepository.findAll();
    }

    public CompanyType getById(Integer id){
        return companyTypeRepository.findById(id).orElse(null);
    }
    public CompanyType update(CompanyType companyType){
        return companyTypeRepository.save(companyType);
    }

}
