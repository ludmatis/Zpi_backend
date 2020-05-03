package com.zpi.zpibackend.repository;

import com.zpi.zpibackend.entity.Event;
import com.zpi.zpibackend.entity.ToDoList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoListRepository extends CrudRepository<ToDoList, Integer> {
    List<ToDoList> findByEvent(Event event);
}
