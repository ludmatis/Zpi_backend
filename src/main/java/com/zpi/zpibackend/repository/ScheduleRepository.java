package com.zpi.zpibackend.repository;

import com.zpi.zpibackend.entity.Event;
import com.zpi.zpibackend.entity.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Integer> {

    List<Schedule> findByEvent(Event event);
}
