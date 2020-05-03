package com.zpi.zpibackend.controller;

import com.zpi.zpibackend.entity.Schedule;
import com.zpi.zpibackend.entity.ScheduleDetail;
import com.zpi.zpibackend.entity.dto.ScheduleDetailDto;
import com.zpi.zpibackend.service.ScheduleDetailService;
import com.zpi.zpibackend.service.ScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/scheduledetail")
public class ScheduleDetailController {

    @Autowired
    private ScheduleDetailService scheduleDetailService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public List<ScheduleDetailDto> getAll(){
        List<ScheduleDetail> scheduleDetails = scheduleDetailService.getAll();
        return scheduleDetails.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    @GetMapping("/getbyscheduleid/{id}")
    public ResponseEntity getBySchedule(@PathVariable Integer id){
        Schedule schedule = scheduleService.getById(id);
        if(schedule == null){
            return ResponseEntity.badRequest().body("Brak podanego harmongramu");
        }
        else{
            List<ScheduleDetail> scheduleDetails = scheduleDetailService.getBySchedule(schedule);
            if(scheduleDetails.isEmpty()){
                return ResponseEntity.badRequest().body("Brak szczegółów podanego harmongramu");
            }
            else {
                List<ScheduleDetailDto> scheduleDetailDtos = scheduleDetails.stream().map(this::convertToDto).collect(Collectors.toList());
                return new ResponseEntity<>(scheduleDetailDtos, HttpStatus.OK);
            }
        }
    }

    @PostMapping("/add")
    public ResponseEntity addScheduleDetail(@RequestBody ScheduleDetailDto scheduleDetailDto){
        ScheduleDetail scheduleDetail = convertFromDto(scheduleDetailDto);
        if(scheduleDetailService.add(scheduleDetail) == null){
            return ResponseEntity.badRequest().body("Cos poszlo nie tak przy dodawaniu");
        }
        else{
            return new ResponseEntity<>(convertToDto(scheduleDetail), HttpStatus.OK);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateScheduleDetail(@RequestBody ScheduleDetailDto scheduleDetailDto, @PathVariable Integer id){
        ScheduleDetail scheduleDetail = scheduleDetailService.getById(id);
        scheduleDetailDto.setScheduledetailid(id);
        if(scheduleDetail == null){
            return ResponseEntity.badRequest().body("Podany szczegół harmonogramu nie istnieje");
        }
        else{
            ScheduleDetail updated = scheduleDetailService.update(convertFromDto(scheduleDetailDto));
            return new ResponseEntity<>(convertToDto(updated), HttpStatus.OK);
        }
    }

    private ScheduleDetailDto convertToDto(ScheduleDetail scheduleDetail){
        return modelMapper.map(scheduleDetail, ScheduleDetailDto.class);
    }

    private ScheduleDetail convertFromDto(ScheduleDetailDto scheduleDetailDto){
        ScheduleDetail scheduleDetail = modelMapper.map(scheduleDetailDto, ScheduleDetail.class);
        Schedule schedule;
        if(scheduleDetailDto.getSchedule() != null){
            schedule = scheduleService.getById(scheduleDetailDto.getSchedule().getScheduleid());
            scheduleDetail.setSchedule(schedule);
        }
        return scheduleDetail;
    }
}
