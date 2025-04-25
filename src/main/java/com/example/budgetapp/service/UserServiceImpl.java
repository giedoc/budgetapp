package com.example.budgetapp.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.budgetapp.dto.AuthRequest;
import com.example.budgetapp.dto.AuthResponse;
import com.example.budgetapp.dto.RegisterRequest;
import com.example.budgetapp.model.User;
import com.example.budgetapp.repository.UserRepository;
import com.example.budgetapp.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService  {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                  .username(request.getUsername())
                  .password(passwordEncoder.encode(request.getPassword()))
                  .role("USER")
                  .build();
        
        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponse(token);
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("Could Not Found User"));
        
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Wrong Password!");
        }

        String token = jwtUtil.generateToken(user.getUsername());

        return new AuthResponse(token);
    }
}
