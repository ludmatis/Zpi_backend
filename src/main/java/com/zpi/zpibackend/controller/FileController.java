package com.zpi.zpibackend.controller;

import com.google.gson.Gson;
import com.zpi.zpibackend.entity.File;
import com.zpi.zpibackend.entity.dto.FileDto;
import com.zpi.zpibackend.service.FileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;
    @Autowired
    private ModelMapper modelMapper;



    @PostMapping(value = "/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file){

        File response = new File();
        if(file.isEmpty())
            return ResponseEntity.badRequest().body("Plik nie został przesłany");

        response.setPath(fileService.save(file));

        return new ResponseEntity<>(convertToDto(response), HttpStatus.OK);
    }

    private FileDto convertToDto(File file){
        return modelMapper.map(file, FileDto.class);
    }
}
