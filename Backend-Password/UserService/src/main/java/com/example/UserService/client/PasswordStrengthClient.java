package com.example.UserService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PasswordService", url = "http://localhost:8081")
public interface PasswordStrengthClient {

    @PostMapping("/api/password/validate")
    String validatePassword(@RequestBody String password);
}
