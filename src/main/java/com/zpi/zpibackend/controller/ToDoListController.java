package com.zpi.zpibackend.controller;

import com.zpi.zpibackend.entity.Event;
import com.zpi.zpibackend.entity.ToDoList;
import com.zpi.zpibackend.entity.ToDoListTask;
import com.zpi.zpibackend.entity.dto.ToDoListDto;
import com.zpi.zpibackend.service.ToDoListService;
import com.zpi.zpibackend.service.EventService;
import com.zpi.zpibackend.service.ToDoListTaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    private ToDoListTaskService toDoListTaskService;
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
        System.out.println(toDoListDto.toString());
        if(toDoListService.add(toDoList)==null){
            return ResponseEntity.badRequest().body("Cos poszlo nie tak przy dodawaniu");
        }
        else
            return new ResponseEntity<>(convertToDto(toDoList),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
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

    @DeleteMapping("/deletetasks/{id}")
    public ResponseEntity deleteTasks(@PathVariable Integer id){
        ToDoList toDoList = toDoListService.getById(id);
        if(toDoList==null){
            return ResponseEntity.badRequest().body("Lista o takim id nie istnieje");
        }
        else{
            var todolisttasks = toDoList.getToDoListTasks();
            var toDoListTasksWithParents = todolisttasks.stream().filter(toDoListTask -> toDoListTask.getParent()!=null);
            var toDoListTasksWithoutParents = todolisttasks.stream().filter(toDoListTask -> toDoListTask.getParent()==null);
            toDoListTasksWithParents.forEach(toDoListTask -> toDoListTaskService.delete(toDoListTask));
            toDoListTasksWithoutParents.forEach(toDoListTask -> toDoListTaskService.delete(toDoListTask));
            return ResponseEntity.ok().body("Usunieto wszystkie zadania");
        }
    }

    public ToDoListDto convertToDto(ToDoList toDoList){
        return modelMapper.map(toDoList, ToDoListDto.class);
    }

    //
    public ToDoList convertFromDto(ToDoListDto toDoListDto){
        ToDoList toDoList = modelMapper.map(toDoListDto, ToDoList.class);

        Event event;
        if(toDoListDto.getEvent() !=null){
            event=eventService.getById(toDoListDto.getEvent().getEventid());
            toDoList.setEvent(event);
        }

        List<ToDoListTask> allTasks = toDoListTaskService.getAll();
        List<ToDoListTask> exactTasks = new ArrayList<>();
        allTasks.forEach(task ->{
            if(task.getToDoList().getTodolistid() == toDoListDto.getTodolistid())
                exactTasks.add(task);
        });
        toDoList.setToDoListTasks(exactTasks);

        return toDoList;
    }
}
