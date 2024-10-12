package com.damian.resilience4j.controller;

import com.damian.resilience4j.feign.ServiceBInvoker;
import com.damian.resilience4j.response.Response;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/serviceA")
@CrossOrigin
@RequiredArgsConstructor
@Log4j2
public class TestController2 {
    private static final String SERVICE_NAME = "serviceA";
    private static final String FALLBACK_METHOD = "fallbackForServiceBFailure";
    private final ServiceBInvoker serviceBInvoker;
    private  int count = 0;

    @GetMapping(path = "/test2")
    @Retry(name = SERVICE_NAME , fallbackMethod = FALLBACK_METHOD)
    public ResponseEntity<Response> getHealth() {
        log.info("Request Propagated to the endpoint!");
        count++;
        log.info("Retrying {} times at {}", count, new Date());
        return serviceBInvoker.getHealth();
    }

    public ResponseEntity<Response> fallbackForServiceBFailure(Exception e) {
        log.info("FallBack method is invoked , Since Service B is Down!");
        return ResponseEntity.ok(new Response(500,
                "FallBack method is invoked , Since Service B is Down!", null));
    }
}
