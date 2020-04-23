package com.zpi.zpibackend.controller;

import com.zpi.zpibackend.entity.ScheduleDetail;
import com.zpi.zpibackend.entity.dto.ScheduleDetailDto;
import com.zpi.zpibackend.service.ScheduleDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/scheduledetail")
public class ScheduleDetailController {

    @Autowired
    private ScheduleDetailService scheduleDetailService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public List<ScheduleDetailDto> getAll(){
        List<ScheduleDetail> scheduleDetails = scheduleDetailService.getAll();
        return scheduleDetails.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private ScheduleDetailDto convertToDto(ScheduleDetail scheduleDetail){
        return modelMapper.map(scheduleDetail, ScheduleDetailDto.class);
    }

    private ScheduleDetail convertFromDto(ScheduleDetailDto scheduleDetailDto){
        return modelMapper.map(scheduleDetailDto, ScheduleDetail.class);
    }
}
