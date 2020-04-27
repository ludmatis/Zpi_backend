package com.zpi.zpibackend.repository;

import com.zpi.zpibackend.entity.Company;
import com.zpi.zpibackend.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Integer> {
}
