package com.zpi.zpibackend.repository;

import com.zpi.zpibackend.entity.CompanyType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyTypeRepository extends CrudRepository<CompanyType, Integer> {
}
