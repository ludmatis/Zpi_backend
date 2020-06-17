package com.zpi.zpibackend.service;

import com.zpi.zpibackend.entity.Event;
import com.zpi.zpibackend.entity.ToDoList;
import com.zpi.zpibackend.entity.ToDoListTask;
import com.zpi.zpibackend.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoListService {

    @Autowired
    private ToDoListRepository toDoListRepository;
    @Autowired
    private ToDoListTaskService toDoListTaskService;

    public ToDoList add(ToDoList toDoList){
        return toDoListRepository.save(toDoList);
    }

    public List<ToDoList> getAll(){
        return (List<ToDoList>) toDoListRepository.findAll();
    }

    public ToDoList getById(Integer id){
        return toDoListRepository.findById(id).orElse(null);
    }

    public ToDoList update(ToDoList toDoList){
        return toDoListRepository.save(toDoList);
    }

    public List<ToDoList> geByEvent(Event event) { return toDoListRepository.findByEvent(event);}

    public void delete(ToDoList toDoList){
        var todolisttasks = toDoList.getToDoListTasks();
        var toDoListTasksWithParents = todolisttasks.stream().filter(toDoListTask -> toDoListTask.getParent()!=null);
        var toDoListTasksWithoutParents = todolisttasks.stream().filter(toDoListTask -> toDoListTask.getParent()==null);
        toDoListTasksWithParents.forEach(toDoListTask -> toDoListTaskService.delete(toDoListTask));
        toDoListTasksWithoutParents.forEach(toDoListTask -> toDoListTaskService.delete(toDoListTask));
        toDoListRepository.delete(toDoList);
    }
}
