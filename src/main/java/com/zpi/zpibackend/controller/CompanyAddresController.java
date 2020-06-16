package com.zpi.zpibackend.controller;

import com.zpi.zpibackend.entity.CompanyAddress;
import com.zpi.zpibackend.entity.dto.CompanyAddressDto;
import com.zpi.zpibackend.service.CompanyAddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin()
@RestController
@RequestMapping("/companyaddress")
public class CompanyAddresController {

    @Autowired
    private CompanyAddressService companyAddressService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public List<CompanyAddressDto> gerAll(){
        List<CompanyAddress> companyAddresses = companyAddressService.getAll();
        return companyAddresses.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private CompanyAddressDto convertToDto(CompanyAddress companyAddress){
        return modelMapper.map(companyAddress, CompanyAddressDto.class);
    }

    private CompanyAddress convertFromDto(CompanyAddressDto companyAddressDto){
        return modelMapper.map(companyAddressDto, CompanyAddress.class);
    }
}
