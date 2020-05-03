package com.zpi.zpibackend.repository;

import com.zpi.zpibackend.entity.ToDoList;
import com.zpi.zpibackend.entity.ToDoListTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoListTaskRepository extends CrudRepository<ToDoListTask, Integer> {
    List<ToDoListTask> findByToDoList(ToDoList toDoList);
}
