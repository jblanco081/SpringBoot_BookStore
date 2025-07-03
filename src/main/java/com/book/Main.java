package com.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= "com.book")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}