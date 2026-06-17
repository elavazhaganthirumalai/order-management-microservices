package com.ela.microservices.customer.dto;

public record CustomerResponse(
        Long id,
        String name,
        String email
) {
}