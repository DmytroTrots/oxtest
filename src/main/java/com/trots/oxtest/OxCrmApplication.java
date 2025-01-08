package com.trots.oxtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class OxCrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(OxCrmApplication.class, args);
    }

}
