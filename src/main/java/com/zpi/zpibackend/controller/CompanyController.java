package com.zpi.zpibackend.controller;

import com.zpi.zpibackend.entity.Company;
import com.zpi.zpibackend.entity.dto.CompanyDto;
import com.zpi.zpibackend.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/get")
    public ResponseEntity getAll(){
        List<Company> companies = companyService.getAll();
        if(companies.isEmpty()){
            return ResponseEntity.badRequest().body("Brak firm w bazie");
        }
        else{
            List<CompanyDto> companyDtos = companies.stream().map(this::convertToDto).collect(Collectors.toList());
            return new ResponseEntity<>(companyDtos, HttpStatus.OK);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getById(@PathVariable Integer id){
        Company company = companyService.getById(id);
        if(company == null){
            return ResponseEntity.badRequest().body("Firma nie istnieje");
        }
        else{
            return new ResponseEntity<>(convertToDto(company), HttpStatus.OK);
        }
    }

    @PostMapping("/addcompany")
    public ResponseEntity addCompany(@RequestBody CompanyDto companyDto){
        companyService.add(convertFromDto(companyDto));
        return new ResponseEntity<>(companyDto, HttpStatus.OK);
    }

    @PutMapping("/updatecompany/{id}")
    public ResponseEntity updateCompany(@RequestBody CompanyDto companyDto, @PathVariable Integer id){
        Company company = companyService.getById(id);
        companyDto.setCompanyid(id);
        if(company == null){
            return ResponseEntity.badRequest().body("Firma nie istnieje");
        }
        else{
            companyService.update(convertFromDto(companyDto));
            return new ResponseEntity<>(companyDto, HttpStatus.OK);
        }
    }


    private CompanyDto convertToDto(Company company){
        return modelMapper.map(company, CompanyDto.class);
    }

    private Company convertFromDto(CompanyDto companyDto){
        return modelMapper.map(companyDto, Company.class);
    }
}
