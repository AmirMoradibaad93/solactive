package com.solactive.solactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
public class SolactiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(SolactiveApplication.class, args);
    }
}
