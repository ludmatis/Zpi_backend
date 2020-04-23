package com.zpi.zpibackend.controller;

import com.zpi.zpibackend.entity.CostOrganizer;
import com.zpi.zpibackend.entity.dto.CostOrganizerDto;
import com.zpi.zpibackend.service.CostOrganizerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/costorganizer")
public class CostOrganizerController {

    @Autowired
    private CostOrganizerService costOrganizerService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public List<CostOrganizerDto> getAll(){
        List<CostOrganizer> costOrganizers = costOrganizerService.getAll();
        return costOrganizers.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private CostOrganizerDto convertToDto(CostOrganizer costOrganizer){
        return modelMapper.map(costOrganizer, CostOrganizerDto.class);
    }

    //TODO as every convertFromDto
    private CostOrganizer convertFromDto(CostOrganizerDto costOrganizerDto){
        return modelMapper.map(costOrganizerDto, CostOrganizer.class);
    }
}
