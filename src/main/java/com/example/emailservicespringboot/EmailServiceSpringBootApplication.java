package com.example.emailservicespringboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
public class EmailServiceSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailServiceSpringBootApplication.class, args);
    }

}
