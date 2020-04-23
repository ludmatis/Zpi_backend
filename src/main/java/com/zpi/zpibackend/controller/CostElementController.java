package com.zpi.zpibackend.controller;

import com.zpi.zpibackend.entity.CostElement;
import com.zpi.zpibackend.entity.dto.CostElementDto;
import com.zpi.zpibackend.service.CostElementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/costelement")
public class CostElementController {

    @Autowired
    private CostElementService costElementService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public List<CostElementDto> getAll(){
        List<CostElement> costElements = costElementService.getAll();
        return costElements.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private CostElementDto convertToDto(CostElement costElement){
        return modelMapper.map(costElement, CostElementDto.class);
    }
    //TODO as every convertFromDto
    private CostElement convertFromDto(CostElementDto costElementDto){
        return modelMapper.map(costElementDto, CostElement.class);
    }
}
