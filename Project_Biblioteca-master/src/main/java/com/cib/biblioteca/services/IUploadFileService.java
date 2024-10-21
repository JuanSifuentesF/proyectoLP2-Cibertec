package com.cib.biblioteca.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IUploadFileService {

    String savePortada(MultipartFile file) throws IOException;
    void deletePortada(String name);

}
