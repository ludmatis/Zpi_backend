package com.zpi.zpibackend.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {


    private final Path fileStorage = Paths.get("D:\\ZPI\\Files");

    public String save(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Path targetLocation = fileStorage.resolve(fileName);
        try {
            Files.copy(file.getInputStream(),targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(targetLocation);
    }
}