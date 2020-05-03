package com.zpi.zpibackend.controller;

import com.sun.xml.bind.v2.TODO;
import com.zpi.zpibackend.entity.ToDoList;
import com.zpi.zpibackend.entity.ToDoListTask;
import com.zpi.zpibackend.entity.dto.ToDoListTaskDto;
import com.zpi.zpibackend.service.ToDoListTaskService;
import com.zpi.zpibackend.service.ToDoListService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todolisttask")
public class ToDoListTaskController {

    @Autowired
    private ToDoListTaskService toDoListTaskService;
    @Autowired
    private ToDoListService toDoListService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public ResponseEntity getAll(){
        List<ToDoListTask> toDoListTasks = toDoListTaskService.getAll();
        if(toDoListTasks.isEmpty())
            return ResponseEntity.badRequest().body("Brak zadan na liscie");
        else {
            List<ToDoListTaskDto> toDoListTaskDtos = toDoListTasks.stream().map(this::convertToDto).collect(Collectors.toList());
            return new ResponseEntity<>(toDoListTaskDtos, HttpStatus.OK);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getToDoListTaskById(@PathVariable Integer id){
        ToDoListTask toDoListTask = toDoListTaskService.getById(id);
        if(toDoListTask==null)
            return ResponseEntity.badRequest().body("Zadanie nie istnieje");
        else
            return new ResponseEntity<>(convertToDto(toDoListTask),HttpStatus.OK);
    }

    @GetMapping("/getbytodolist/{id}")
    public ResponseEntity getToDoListTasksByToDoListId(@PathVariable Integer id){
        ToDoList toDoList =toDoListService.getById(id);
        if(toDoList==null)
            return ResponseEntity.badRequest().body("Lista zadan nie istnieje");
        else{
            List<ToDoListTask> toDoListTasks = toDoListTaskService.getByToDoList(toDoList);
            if(toDoListTasks ==null)
                return ResponseEntity.badRequest().body("Lista zadan nie ma zadnych zadan");
            List<ToDoListTaskDto> toDoListTaskDtos = toDoListTasks.stream().map(this::convertToDto).collect(Collectors.toList());
            return new ResponseEntity<>(toDoListTaskDtos,HttpStatus.OK);
        }
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody ToDoListTaskDto toDoListTaskDto){
        ToDoListTask toDoListTask = convertFromDto(toDoListTaskDto);
        if(toDoListTaskService.add(toDoListTask) == null)
            return ResponseEntity.badRequest().body("Wystapil blad przy dodawaniu");
        else
            return new ResponseEntity<>(convertToDto(toDoListTask),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateToDoListTask(@RequestBody ToDoListTaskDto toDoListTaskDto, @PathVariable Integer id){
        ToDoListTask toDoListTask = toDoListTaskService.getById(id);
        toDoListTaskDto.setTaskid(id);
        if(toDoListTask == null) {
            return ResponseEntity.badRequest().body("Zadanie nie istnieje");
        }
        else{
            ToDoListTask updated = toDoListTaskService.update(convertFromDto(toDoListTaskDto));
            return new ResponseEntity<>(convertToDto(updated),HttpStatus.OK);
        }
    }

    private ToDoListTaskDto convertToDto(ToDoListTask toDoListTask){
        return modelMapper.map(toDoListTask, ToDoListTaskDto.class);
    }

    //TODO to update if entity owns a list of other entities
    private ToDoListTask convertFromDto(ToDoListTaskDto toDoListTaskDto){
       ToDoListTask toDoListTask = modelMapper.map(toDoListTaskDto, ToDoListTask.class);
       ToDoList toDoList;
       if(toDoListTaskDto.getToDoList() != null){
           toDoList = toDoListService.getById(toDoListTaskDto.getToDoList().getTodolistid());
           toDoListTask.setToDoList(toDoList);
       }
       return toDoListTask;
    }
}
