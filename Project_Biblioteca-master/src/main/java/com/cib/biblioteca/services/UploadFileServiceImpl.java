package com.cib.biblioteca.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadFileServiceImpl implements IUploadFileService {
    private String folder = "portadas//";

    public String savePortada(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(folder + file.getOriginalFilename());
            Files.write(path, bytes);
            return file.getOriginalFilename();
        }
        return "default.png";
    }

    public void deletePortada(String name) {
        String path = "portadas//";
        File file = new File(path + name);
        file.delete();
    }
}
