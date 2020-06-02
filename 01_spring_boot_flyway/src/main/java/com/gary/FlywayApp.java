package com.gary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class FlywayApp {

    public static void main(String[] args){
        SpringApplication.run(FlywayApp.class,args);
    }

}