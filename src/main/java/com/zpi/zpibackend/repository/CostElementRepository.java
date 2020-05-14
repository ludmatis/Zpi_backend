package com.zpi.zpibackend.repository;

import com.zpi.zpibackend.entity.CostElement;
import com.zpi.zpibackend.entity.CostOrganizer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CostElementRepository extends CrudRepository<CostElement, Integer> {
    List<CostElement> findByCostOrganizer(CostOrganizer costOrganizer);
    List<CostElement> findByParent(CostElement parent);
}
