package com.zpi.zpibackend.repository;

import com.zpi.zpibackend.entity.CompanyAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyAddressRepository extends CrudRepository<CompanyAddress, Integer> {
}
