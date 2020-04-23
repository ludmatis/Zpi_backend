package com.zpi.zpibackend.repository;

import com.zpi.zpibackend.entity.EventDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDetailRepository extends CrudRepository<EventDetail, Integer> {
}
