package com.zpi.zpibackend.repository;

import com.zpi.zpibackend.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
     Optional<Person> findByEmailAndPassword(String email, String password);
     Optional<Person> findByEmail(String email);
     List<Optional<Person>> findAllByEmail(String email);
}
