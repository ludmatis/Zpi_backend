package com.zpi.zpibackend.controller;

import com.zpi.zpibackend.entity.Company;
import com.zpi.zpibackend.entity.dto.CompanyDto;
import com.zpi.zpibackend.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public List<CompanyDto> getAll(){
        List<Company> companies = companyService.getAll();
        return companies.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private CompanyDto convertToDto(Company company){
        return modelMapper.map(company, CompanyDto.class);
    }

    private Company convertFromDto(CompanyDto companyDto){
        return modelMapper.map(companyDto, Company.class);
    }
}
