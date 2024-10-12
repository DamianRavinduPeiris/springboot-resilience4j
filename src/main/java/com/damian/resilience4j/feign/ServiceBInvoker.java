package com.damian.resilience4j.feign;

import com.damian.resilience4j.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "service-b", url = "http://localhost:8081/serviceB")
public interface ServiceBInvoker {

    @GetMapping(path = "/health")
    ResponseEntity<Response> getHealth();
}
