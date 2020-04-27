package com.zpi.zpibackend.service;

import com.zpi.zpibackend.entity.Person;
import com.zpi.zpibackend.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person add(Person person){
        return personRepository.save(person);
    }

    public List<Person> getAll(){
        return (List<Person>) personRepository.findAll();
    }

    public Person getByID(Integer id){
        return personRepository.findById(id).orElse(null);
    }

    public Person getByEmail(String email){
        return personRepository.findByEmail(email).orElse(null);
    }
    public Person updatePerson(Person person){
        return personRepository.save(person);
    }

}
