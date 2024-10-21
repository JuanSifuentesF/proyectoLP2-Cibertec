package com.cib.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjectBibliotecaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectBibliotecaApplication.class, args);
    }

}
