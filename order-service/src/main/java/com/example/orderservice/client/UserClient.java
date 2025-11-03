package com.example.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user-service", url = "http://user-service:8081")
public interface UserClient {
    @GetMapping("/users/health")
    String health();
}
