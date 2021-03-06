package com.zpi.zpibackend.service;

import com.zpi.zpibackend.entity.Message;
import com.zpi.zpibackend.entity.Person;
import com.zpi.zpibackend.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message add(Message message){
        return messageRepository.save(message);
    }

    public List<Message> getAll(){
        return (List<Message>) messageRepository.findAll();
    }

    public Message getById(Integer id){
        return messageRepository.findById(id).orElse(null);
    }

    public List<Message> getBySender(Person person){
        return messageRepository.findBySender(person);
    }

    public List<Message> getByReceiver(Person person){
        return messageRepository.findByReceiver(person);
    }

    public Message update(Message message){
        return messageRepository.save(message);
    }

}
