package com.zpi.zpibackend.controller;

import com.sun.xml.bind.v2.TODO;
import com.zpi.zpibackend.entity.Event;
import com.zpi.zpibackend.entity.ToDoList;
import com.zpi.zpibackend.entity.dto.ToDoListDto;
import com.zpi.zpibackend.service.ToDoListService;
import com.zpi.zpibackend.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todolist")
public class ToDoListController {

    @Autowired
    private ToDoListService toDoListService;
    @Autowired
    private EventService eventService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public ResponseEntity getAll(){
        List<ToDoList> toDoLists = toDoListService.getAll();
        if(toDoLists.isEmpty())
            return ResponseEntity.badRequest().body("Brak list zadań w bazie");
        else{
            List<ToDoListDto> toDoListDtos = toDoLists.stream().map(this::convertToDto).collect(Collectors.toList());
            return new ResponseEntity<>(toDoListDtos, HttpStatus.OK);
        }
    }


    @GetMapping("/get/{id}")
    public ResponseEntity getToDoListById(@PathVariable Integer id) {
        ToDoList toDoList = toDoListService.getById(id);
        if(toDoList==null)
            return ResponseEntity.badRequest().body("Lista zadan nie istnieje");
        else
            return new ResponseEntity<>(convertToDto(toDoList),HttpStatus.OK);
    }

    @GetMapping("/getbyevent/{id}")
    public ResponseEntity getToDoListsByEventId(@PathVariable Integer id){
        Event event = eventService.getById(id);
        if(event==null){
            return ResponseEntity.badRequest().body("Event nie istnieje");
        }
        else{
            List<ToDoList> toDoLists = toDoListService.geByEvent(event);
            if(toDoLists==null)
                return ResponseEntity.badRequest().body("Event nie ma list zadań");
            List<ToDoListDto> toDoListDtos = toDoLists.stream().map(this::convertToDto).collect(Collectors.toList());
            return new ResponseEntity<>(toDoListDtos,HttpStatus.OK);
        }

    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody ToDoListDto toDoListDto){
        ToDoList toDoList = convertFromDto(toDoListDto);
        if(toDoListService.add(toDoList)==null){
            return ResponseEntity.badRequest().body("Cos poszlo nie tak przy dodawaniu");
        }
        else
            return new ResponseEntity<>(convertToDto(toDoList),HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity updateToDoList(@RequestBody ToDoListDto toDoListDto, @PathVariable Integer id){
        ToDoList toDoList = toDoListService.getById(id);
        toDoListDto.setTodolistid(id);
        if(toDoList==null)
            return ResponseEntity.badRequest().body("Lista zadan nie istnieje");
        else{
            ToDoList updated =toDoListService.update(convertFromDto(toDoListDto));
            return new ResponseEntity<>(convertToDto(updated),HttpStatus.OK);
        }

    }

    public ToDoListDto convertToDto(ToDoList toDoList){
        return modelMapper.map(toDoList, ToDoListDto.class);
    }

    //TODO convertFromDto to update if entity owns a list
    public ToDoList convertFromDto(ToDoListDto toDoListDto){
        ToDoList toDoList = modelMapper.map(toDoListDto, ToDoList.class);
        Event event;
        if(toDoListDto.getEvent() !=null){
            event=eventService.getById(toDoListDto.getEvent().getEventid());
            toDoList.setEvent(event);
        }
        return toDoList;
    }
}
