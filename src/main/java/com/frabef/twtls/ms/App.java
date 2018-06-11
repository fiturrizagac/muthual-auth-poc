package com.frabef.twtls.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.frabef.twtls.ms.config"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

}
