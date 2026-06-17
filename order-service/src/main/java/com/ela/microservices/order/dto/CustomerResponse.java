package com.ela.microservices.order.dto;

public record CustomerResponse(
        Long id,
        String name,
        String email
) {
}