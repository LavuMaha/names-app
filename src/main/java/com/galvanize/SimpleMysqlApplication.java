package com.galvanize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class SimpleMysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleMysqlApplication.class, args);
    }

}
