package com.zpi.zpibackend.repository;

import com.zpi.zpibackend.entity.Event;
import com.zpi.zpibackend.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {

    List<Event> findByCreator(Person person);
}
