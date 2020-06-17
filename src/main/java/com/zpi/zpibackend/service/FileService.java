package com.zpi.zpibackend.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {


    private final Path fileStorage = Paths.get("/root/zpi/photos");

    public String save(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Path targetLocation = fileStorage.resolve(fileName);
        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String path = targetLocation.toString();
        path = path.replace("\\", "/");
        return path;
    }


    public Resource loadFileAsResource(String fileName) {

            Path filePath = this.fileStorage.resolve(fileName).normalize();
            Resource resource = null;
            try {
                resource = new UrlResource(filePath.toUri());
            } catch (MalformedURLException e) {

                e.printStackTrace();
            }
           return  resource;

    }
}
