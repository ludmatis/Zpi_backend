package com.zpi.zpibackend.controller;

import com.zpi.zpibackend.entity.Message;
import com.zpi.zpibackend.entity.dto.MessageDto;
import com.zpi.zpibackend.service.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/message")
public class MesssageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public List<MessageDto> getAll(){
        List<Message> messages = messageService.getAll();
        return messages.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/ok")
    public List<Message> getAll2(){
        return messageService.getAll();
    }
    private MessageDto convertToDto(Message message){
        return modelMapper.map(message, MessageDto.class);
    }

    //TODO as every convertFromDto
    private Message convertFromDto(MessageDto messageDto){
        return modelMapper.map(messageDto, Message.class);
    }
}
