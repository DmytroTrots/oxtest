package com.trots.oxtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableCaching
@EnableTransactionManagement
public class OxCrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(OxCrmApplication.class, args);
    }

}
