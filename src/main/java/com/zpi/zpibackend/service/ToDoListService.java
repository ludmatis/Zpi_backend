package com.zpi.zpibackend.service;

import com.zpi.zpibackend.entity.ToDoList;
import com.zpi.zpibackend.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoListService {

    @Autowired
    private ToDoListRepository toDoListRepository;

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

}
