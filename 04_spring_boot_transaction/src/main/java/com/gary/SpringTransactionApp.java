package com.gary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class SpringTransactionApp {

    public static void main(String[] args){
        SpringApplication.run(SpringTransactionApp.class,args);
    }

}