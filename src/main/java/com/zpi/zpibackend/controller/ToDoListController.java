package com.zpi.zpibackend.controller;

import com.sun.xml.bind.v2.TODO;
import com.zpi.zpibackend.entity.ToDoList;
import com.zpi.zpibackend.entity.dto.ToDoListDto;
import com.zpi.zpibackend.service.ToDoListService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todolist")
public class ToDoListController {

    @Autowired
    private ToDoListService toDoListService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public List<ToDoListDto> getAll(){
        List<ToDoList> toDoLists = toDoListService.getAll();
        return toDoLists.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private ToDoListDto convertToDto(ToDoList toDoList){
        return modelMapper.map(toDoList, ToDoListDto.class);
    }

    //TODO convertFromDto to update if entity owns a list
    private ToDoList convertFromDto(ToDoListDto toDoListDto){
        return modelMapper.map(toDoListDto, ToDoList.class);
    }
}
