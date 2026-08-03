package com.ela.microservices.auth.dto;

public record CustomerResponse(
        Long id,
        String name,
        String email
) {
}