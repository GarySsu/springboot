package com.gary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringSecurityApp {

    public static void main(String[] args){
        SpringApplication.run(SpringSecurityApp.class,args);
    }

}