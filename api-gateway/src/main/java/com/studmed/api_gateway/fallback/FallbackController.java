package com.studmed.api_gateway.fallback;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @RequestMapping("/evaluationFallback")
    public ResponseEntity<String> evaluationFallback() {
        return ResponseEntity.status(500).body("Evaluation Service is currently unavailable. Please try again later");
    }

    @RequestMapping("/notificationFallback")
    public ResponseEntity<String> notificationFallback() {
        return ResponseEntity.status(500).body("Notification Service is currently unavailable. Please try again later");
    }

    @RequestMapping("/userFallback")
    public ResponseEntity<String> userFallback() {
        return ResponseEntity.status(500).body("User Service is currently unavailable. Please try again later");
    }

    @RequestMapping("/orderFallback")
    public ResponseEntity<String> orderFallback() {
        return ResponseEntity.status(500).body("Order Service is currently unavailable. Please try again later");
    }

    @RequestMapping("/soporteFallback")
    public ResponseEntity<String> tripFallback() {
        return ResponseEntity.status(500).body("Soporte Service is currently unavailable. Please try again later");
    }
}