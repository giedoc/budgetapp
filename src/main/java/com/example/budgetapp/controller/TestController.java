package com.example.budgetapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    
    @GetMapping("/api/test")
    public String securedEndpoint() {
        return "✔ Token verified. Only logged in users can access this endpoint.";
    }
}
