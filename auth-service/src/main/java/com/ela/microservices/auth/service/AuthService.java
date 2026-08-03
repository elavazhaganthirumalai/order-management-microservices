package com.ela.microservices.auth.service;

import com.ela.microservices.auth.dto.UserLoginRequestDTO;
import com.ela.microservices.auth.entity.User;
import com.ela.microservices.auth.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public String loginUser(UserLoginRequestDTO userRequest) {
        User user = repository.findByEmail(userRequest.email()).orElseThrow(() -> new RuntimeException("User not found with email: " + userRequest.email()));

        if (!passwordEncoder.matches(userRequest.password(), user.getPassword())) {
            return null;
        }

        return jwtService.generateToken(user);
    }
}
