package com.zpi.zpibackend.service;

import com.zpi.zpibackend.entity.ToDoListTask;
import com.zpi.zpibackend.repository.ToDoListTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoListTaskService {

    @Autowired
    private ToDoListTaskRepository toDoListTaskRepository;

    public ToDoListTask add(ToDoListTask toDoListTask){
        return toDoListTaskRepository.save(toDoListTask);
    }

    public List<ToDoListTask> getAll(){
        return (List<ToDoListTask>) toDoListTaskRepository.findAll();
    }

    public ToDoListTask getById(Integer id){
        return toDoListTaskRepository.findById(id).orElse(null);
    }

    public ToDoListTask update(ToDoListTask toDoListTask){
        return toDoListTaskRepository.save(toDoListTask);
    }

}
