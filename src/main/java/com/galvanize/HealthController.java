package com.galvanize;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health")
public class HealthController {
    @GetMapping
    public ResponseEntity getHealth(@RequestHeader(value = "User-Agent") String userAgent){
        System.out.printf("User Agent: %s\n", userAgent);
        System.out.println("It's healthy!");
        return ResponseEntity.ok("It's healthy!");
    }
}