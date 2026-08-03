package com.ela.microservices.auth.dto;


public record UserLoginRequestDTO(
        String email,
        String password
) {}
