package com.zpi.zpibackend.repository;

import com.zpi.zpibackend.entity.ScheduleDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleDetailRepository extends CrudRepository<ScheduleDetail, Integer> {
}
