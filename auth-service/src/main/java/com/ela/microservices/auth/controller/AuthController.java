package com.ela.microservices.auth.controller;

import com.ela.microservices.auth.dto.LoginResponseDTO;
import com.ela.microservices.auth.dto.UserLoginRequestDTO;
import com.ela.microservices.auth.entity.User;
import com.ela.microservices.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody User user){
        User userData = authService.saveUser(user);
        if (Objects.isNull(userData)){
            return ResponseEntity.status(500).body("User Not Registered Due To Some Error");
        } else {
            return ResponseEntity.status(201).body("User Registered Successfully");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequestDTO userRequest){
        String token = authService.loginUser(userRequest);
        if (token != null){
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } else {
            return ResponseEntity.status(400).body("Email or Password is Incorrect");
        }
    }


}
