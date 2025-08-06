package com.studmed.soporte.client;

import com.studmed.soporte.domain.model.client.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user")
public interface UserClient {
    @GetMapping("/users/{id}")
    ResponseEntity<User> getUserById(@PathVariable Long id);
}
