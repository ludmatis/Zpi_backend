package com.zpi.zpibackend.repository;

import com.zpi.zpibackend.entity.CostOrganizer;
import com.zpi.zpibackend.entity.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CostOrganizerRepository extends CrudRepository<CostOrganizer, Integer> {
    List<CostOrganizer> findByEvent(Event event);
}
