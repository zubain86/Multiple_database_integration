package com.example.mdi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.mdi.dao")
public class MdiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MdiApplication.class, args);
    }

}
