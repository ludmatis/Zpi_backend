package com.zpi.zpibackend.controller;

import com.zpi.zpibackend.entity.Company;
import com.zpi.zpibackend.entity.CompanyType;
import com.zpi.zpibackend.entity.dto.CompanyDto;
import com.zpi.zpibackend.entity.dto.CompanyTypeDto;
import com.zpi.zpibackend.service.CompanyService;
import com.zpi.zpibackend.service.CompanyTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/companytype")
public class CompanyTypeController {

    @Autowired
    private CompanyTypeService companyTypeService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public List<CompanyTypeDto> getAll(){
        List<CompanyType> companyTypes = companyTypeService.getAll();
        return  companyTypes.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private CompanyTypeDto convertToDto(CompanyType companyType){
        return modelMapper.map(companyType, CompanyTypeDto.class);
    }
    /*TODO when converting from dto we need to fill List in Entity CompanyType to
        follow key restrictions, need to be done in every controller which type contains list of other entities*/
    private CompanyType convertFromDto(CompanyTypeDto companyTypeDto){
        List<Company> all_companies = companyService.getAll();
        List<Company> exact_companies = new ArrayList<>();
        all_companies.forEach(company -> {
            if(company.getCompanyType().getCompanytypeid() == companyTypeDto.getCompanytypeid()){
                exact_companies.add(company);
            }
        });
        CompanyType company_type =  modelMapper.map(companyTypeDto, CompanyType.class);
        company_type.setCompanies(exact_companies);
        return company_type;
    }
}
