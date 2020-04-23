package com.zpi.zpibackend.service;

import com.zpi.zpibackend.entity.Company;
import com.zpi.zpibackend.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company add(Company company){
        return companyRepository.save(company);
    }

    public List<Company> getAll() {
        return (List<Company>) companyRepository.findAll();
    }

    public Company get(Integer id){
        return companyRepository.findById(id).get();
    }
}
