package com.zpi.zpibackend.repository;

import com.zpi.zpibackend.entity.CostElement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostElementRepository extends CrudRepository<CostElement, Integer> {
}
