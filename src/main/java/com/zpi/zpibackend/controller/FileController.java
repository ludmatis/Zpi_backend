package com.zpi.zpibackend.controller;

import com.zpi.zpibackend.entity.File;
import com.zpi.zpibackend.entity.dto.FileDto;
import com.zpi.zpibackend.service.FileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;
    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/get/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {

        Resource resource = fileService.loadFileAsResource(fileName);
        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }


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
