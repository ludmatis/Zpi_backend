package com.zpi.zpibackend.repository;

import com.zpi.zpibackend.entity.EventPerson;
import com.zpi.zpibackend.entity.composite_key.EventPersonId;
import org.springframework.data.repository.CrudRepository;

public interface EventPersonRepository extends CrudRepository<EventPerson, Integer> {
    EventPerson findByEventPersonId(EventPersonId id);
}
