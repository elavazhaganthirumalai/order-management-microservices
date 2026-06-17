package com.ela.microservices.notification.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @RabbitListener(
            queues = "notification.queue"
    )
    public void consume(
            OrderCreatedEvent event) {

        System.out.println(
                "Notification Sent for Order : "
                        + event.orderId()
        );
    }
}