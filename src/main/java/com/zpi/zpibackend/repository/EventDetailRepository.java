package com.zpi.zpibackend.repository;

import com.zpi.zpibackend.entity.Event;
import com.zpi.zpibackend.entity.EventDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventDetailRepository extends CrudRepository<EventDetail, Integer> {
    List<EventDetail> findByEvent(Event event);
}
