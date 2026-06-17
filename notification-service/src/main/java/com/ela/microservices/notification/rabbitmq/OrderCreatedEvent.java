package com.ela.microservices.notification.rabbitmq;

public record OrderCreatedEvent(
        Long orderId,
        Long customerId,
        String productName
) {
}
