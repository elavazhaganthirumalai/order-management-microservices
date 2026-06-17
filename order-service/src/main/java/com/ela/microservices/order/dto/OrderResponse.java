package com.ela.microservices.order.dto;

public record OrderResponse(
        Long id,
        Long customerId,
        String productName,
        Long amount
) {}
