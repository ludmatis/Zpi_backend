package com.zpi.zpibackend.repository;

import com.zpi.zpibackend.entity.Schedule;
import com.zpi.zpibackend.entity.ScheduleDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleDetailRepository extends CrudRepository<ScheduleDetail, Integer> {

    List<ScheduleDetail> findBySchedule(Schedule schedule);
}
