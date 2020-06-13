package com.zpi.zpibackend.controller;

import com.sun.xml.bind.v2.TODO;
import com.zpi.zpibackend.entity.EventPerson;
import com.zpi.zpibackend.entity.ToDoList;
import com.zpi.zpibackend.entity.ToDoListTask;
import com.zpi.zpibackend.entity.composite_key.EventPersonId;
import com.zpi.zpibackend.entity.dto.ToDoListTaskDto;
import com.zpi.zpibackend.service.EventPersonService;
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
    private  EventPersonService eventPersonService;
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

    @GetMapping("/getbyexecutor/{personid}/{eventid}")
    public ResponseEntity getToDoListTasksByExecutor(@PathVariable(value = "personid") Integer personId, @PathVariable(value = "eventid") Integer eventId) {
        EventPersonId eventPersonId = new EventPersonId(personId,eventId);
        EventPerson eventPerson = eventPersonService.getById(eventPersonId);
        if(eventPerson==null)
            return  ResponseEntity.badRequest().body("Osoba nie istnieje");
        else{
            List<ToDoListTask> toDoListTasks = toDoListTaskService.getByEventPerson(eventPerson);
            if(toDoListTasks ==null)
                return ResponseEntity.badRequest().body("Lista zadan nie ma zadnych zadan");
            List<ToDoListTaskDto> toDoListTaskDtos = toDoListTasks.stream().map(this::convertToDto).collect(Collectors.toList());
            return new ResponseEntity<>(toDoListTaskDtos,HttpStatus.OK);
        }
    }

    @GetMapping("/getbyparent/{id}")
    public ResponseEntity getToDoListTaskByParent(@PathVariable Integer id){
        ToDoListTask toDoListTask = toDoListTaskService.getById(id);
        if(toDoListTask == null){
            return ResponseEntity.badRequest().body("Rodzic nie istnieje");
        }
        else{
            List<ToDoListTask> toDoListTasks = toDoListTaskService.getByParent(toDoListTask);
            List<ToDoListTaskDto> toDoListTaskDtos = toDoListTasks.stream().map(this::convertToDto).collect(Collectors.toList());
            if(toDoListTaskDtos.isEmpty())
                return ResponseEntity.badRequest().body("Zadanie nie posiada dzieci");
            else
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

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteToDoListTask(@PathVariable Integer id){
        ToDoListTask toDoListTask = toDoListTaskService.getById(id);
        if(toDoListTask == null){
            return ResponseEntity.badRequest().body("Zadanie o podanym id nie istnieje");
        }
        else{
            toDoListTaskService.delete(toDoListTask);
            return ResponseEntity.ok().body("Zadanie usunieto");
        }
    }
    private ToDoListTaskDto convertToDto(ToDoListTask toDoListTask){
        return modelMapper.map(toDoListTask, ToDoListTaskDto.class);
    }


    private ToDoListTask convertFromDto(ToDoListTaskDto toDoListTaskDto){
       ToDoListTask toDoListTask = modelMapper.map(toDoListTaskDto, ToDoListTask.class);
       ToDoList toDoList;
       ToDoListTask parent;
       EventPerson executor;

       if(toDoListTaskDto.getToDoList() != null){
           toDoList = toDoListService.getById(toDoListTaskDto.getToDoList().getTodolistid());
           toDoListTask.setToDoList(toDoList);
       }
       if(toDoListTaskDto.getParent() != null){
        parent = toDoListTaskService.getById(toDoListTaskDto.getParent().getTaskid());
        toDoListTask.setParent(parent);
       }
       if(toDoListTaskDto.getExecutor() != null){
           executor = eventPersonService.getById(toDoListTaskDto.getExecutor().getEventPersonId());
           toDoListTask.setExecutor(executor);
       }
       return toDoListTask;
    }
}
