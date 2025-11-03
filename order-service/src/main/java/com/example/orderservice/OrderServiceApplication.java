package com.example.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.example.orderservice.client.UserClient;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

@RestController
class OrderController {

    @Autowired
    private UserClient userClient;
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/orders/health")
    public String health() {
        return "order-service OK";
    }

    @GetMapping("/orders/test-user")
    public String getUserHealth() {
        // Call user-service endpoint
        String response = restTemplate.getForObject("http://localhost:8081/users/health", String.class);
        return "Order-service calling user-service → " + response;
    }

    @GetMapping("/orders/test-user2")
    public String callUserService() {
        String userHealth = userClient.health();
        return "Order-service (via Feign) → " + userHealth;
    }
}
