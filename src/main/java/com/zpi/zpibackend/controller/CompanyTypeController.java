package com.zpi.zpibackend.controller;

import com.zpi.zpibackend.entity.Company;
import com.zpi.zpibackend.entity.CompanyType;
import com.zpi.zpibackend.entity.dto.CompanyDto;
import com.zpi.zpibackend.entity.dto.CompanyTypeDto;
import com.zpi.zpibackend.service.CompanyService;
import com.zpi.zpibackend.service.CompanyTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin()
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
    public ResponseEntity getAll(){
        List<CompanyType> companyTypes = companyTypeService.getAll();
        if(companyTypes.isEmpty()){
            return ResponseEntity.badRequest().body("Brak typów firm w bazie");
        }
        else{
            List<CompanyTypeDto> companyTypeDtos = companyTypes.stream().map(this::convertToDto).collect(Collectors.toList());
            return new ResponseEntity<>(companyTypeDtos, HttpStatus.OK);
        }
    }

    @PostMapping("/add")
    public ResponseEntity addCompanyType(@RequestBody CompanyTypeDto companyTypeDto){
        CompanyType companyType = convertFromDto(companyTypeDto);
        if(companyTypeService.add(companyType) == null){
            return ResponseEntity.badRequest().body("Cos poszlo nie tak przy dodawaniu");
        }
        else {
            return new ResponseEntity<>(companyTypeDto, HttpStatus.OK);
        }
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
