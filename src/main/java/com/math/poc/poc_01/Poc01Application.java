package com.math.poc.poc_01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;

@SpringBootApplication
public class Poc01Application {

    public static void main(String[] args) {
        SpringApplication.run(Poc01Application.class, args);
    }

}
