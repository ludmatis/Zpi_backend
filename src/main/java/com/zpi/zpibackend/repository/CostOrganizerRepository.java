package com.zpi.zpibackend.repository;

import com.zpi.zpibackend.entity.CostOrganizer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostOrganizerRepository extends CrudRepository<CostOrganizer, Integer> {
}
