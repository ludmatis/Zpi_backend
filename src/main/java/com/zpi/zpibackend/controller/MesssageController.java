package com.zpi.zpibackend.controller;

import com.zpi.zpibackend.entity.Message;
import com.zpi.zpibackend.entity.Person;
import com.zpi.zpibackend.entity.dto.MessageDto;
import com.zpi.zpibackend.service.MessageService;
import com.zpi.zpibackend.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/message")
public class MesssageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private PersonService personService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public ResponseEntity getAll(){
        List<Message> messages = messageService.getAll();
        if(messages.isEmpty())
            return ResponseEntity.badRequest().body("Nie istnieja żadne wiadomości");
        else{
            List<MessageDto> messageDtos = messages.stream().map(this::convertToDto).collect(Collectors.toList());
            return new ResponseEntity<>(messageDtos,HttpStatus.OK);
        }
    }
    @GetMapping("/get/{id}")
    public ResponseEntity getById(@PathVariable Integer id){
        Message message = messageService.getById(id);
        if(message == null)
            return ResponseEntity.badRequest().body("Wiadomosc nie istnieje");
        else
            return  new ResponseEntity<>(convertToDto(message),HttpStatus.OK);
    }
    /*
    @GetMapping("/ok")
    public List<Message> getAll2(){
        return messageService.getAll();
    }
    */
    @GetMapping("/getsent/{id}")
    public ResponseEntity getSent(@PathVariable Integer id) {
        Person sender = personService.getByID(id);
        if(sender==null)
            return ResponseEntity.badRequest().body("Uzytkownik nie istnieje");
        else {
            List<Message> sentMessages = messageService.getBySender(sender);
            if(sentMessages.isEmpty())
                return  ResponseEntity.badRequest().body("Uzytkownik nie wysłał żadnych wiadomości");
            List<MessageDto> sentDtos = sentMessages.stream().map(this::convertToDto).collect(Collectors.toList());
            return new ResponseEntity<>(sentDtos, HttpStatus.OK);
        }
    }

    @GetMapping("/getreceived/{id}")
    public ResponseEntity getReceived(@PathVariable Integer id) {
        Person receiver = personService.getByID(id);
        if(receiver==null)
            return ResponseEntity.badRequest().body("Uzytkownik nie istnieje");
        else{
            List<Message> receivedMessages = messageService.getByReceiver(receiver);
            if(receivedMessages.isEmpty())
                return ResponseEntity.badRequest().body("Uzytkownik nie otrzymał żadnych wiadomości");
            List<MessageDto> receivedDtos = receivedMessages.stream().map(this::convertToDto).collect(Collectors.toList());
            return new ResponseEntity<>(receivedDtos,HttpStatus.OK);
        }
    }

    @PostMapping("/send")
    public ResponseEntity send(@RequestBody MessageDto messageDto) {
        Message message = convertFromDto(messageDto);
        if(messageService.add(message)==null)
            return ResponseEntity.badRequest().body("Blad przy wysylaniu wiadomosci");
        else
            return new ResponseEntity<>(convertToDto(message),HttpStatus.OK);
    }

    private MessageDto convertToDto(Message message){
        return modelMapper.map(message, MessageDto.class);
    }

    private Message convertFromDto(MessageDto messageDto){
        Message message = modelMapper.map(messageDto, Message.class);
        Person sender;
        Person receiver;
        if(messageDto.getSender() != null){
            sender=personService.getByID(messageDto.getSender().getPersonid());
            message.setSender(sender);
        }
        if(messageDto.getReceiver() != null){
            receiver=personService.getByID(messageDto.getReceiver().getPersonid());
            message.setReceiver(receiver);
        }
        return message;
    }
}
