package com.zpi.zpibackend.controller;

import com.zpi.zpibackend.entity.ToDoList;
import com.zpi.zpibackend.entity.ToDoListTask;
import com.zpi.zpibackend.entity.dto.ToDoListTaskDto;
import com.zpi.zpibackend.service.ToDoListTaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todolisttask")
public class ToDoListTaskController {

    @Autowired
    private ToDoListTaskService toDoListTaskService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public List<ToDoListTaskDto> getAll(){
        List<ToDoListTask> toDoListTasks = toDoListTaskService.getAll();
        return toDoListTasks.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private ToDoListTaskDto convertToDto(ToDoListTask toDoListTask){
        return modelMapper.map(toDoListTask, ToDoListTaskDto.class);
    }

    //TODO to update if entity owns a list of other entities
    private ToDoListTask convertFromDto(ToDoListTaskDto toDoListTaskDto){
        return modelMapper.map(toDoListTaskDto, ToDoListTask.class);
    }
}
