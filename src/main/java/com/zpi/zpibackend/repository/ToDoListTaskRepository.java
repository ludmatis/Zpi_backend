package com.zpi.zpibackend.repository;

import com.zpi.zpibackend.entity.ToDoListTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoListTaskRepository extends CrudRepository<ToDoListTask, Integer> {
}
