package com.example.budgetapp.service;

import com.example.budgetapp.dto.AuthRequest;
import com.example.budgetapp.dto.AuthResponse;
import com.example.budgetapp.dto.RegisterRequest;

public interface UserService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(AuthRequest request);
    
}