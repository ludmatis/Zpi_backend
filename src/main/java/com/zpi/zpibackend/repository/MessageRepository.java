package com.zpi.zpibackend.repository;

import com.zpi.zpibackend.entity.Message;
import com.zpi.zpibackend.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {
    List<Message> findBySender(Person person);

    List<Message> findByReceiver(Person person);
}
