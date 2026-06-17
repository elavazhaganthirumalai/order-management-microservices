package com.ela.microservices.order.rabbitmq;

public record OrderCreatedEvent(
        Long orderId,
        Long customerId,
        String productName
) {
}
