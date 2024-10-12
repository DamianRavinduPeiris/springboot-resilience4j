package com.damian.resilience4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServiceAResilience4jApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceAResilience4jApplication.class, args);
    }

}
