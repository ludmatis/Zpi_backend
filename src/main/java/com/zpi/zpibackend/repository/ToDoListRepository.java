package com.zpi.zpibackend.repository;

import com.zpi.zpibackend.entity.ToDoList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoListRepository extends CrudRepository<ToDoList, Integer> {
}
