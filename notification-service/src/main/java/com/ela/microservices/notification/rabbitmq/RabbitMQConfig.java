package com.ela.microservices.notification.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("order.exchange");
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue("notification.queue");
    }

    @Bean
    public Binding notificationBinding(
            Queue notificationQueue,
            TopicExchange exchange) {

        return BindingBuilder
                .bind(notificationQueue)
                .to(exchange)
                .with("order.created");
    }
}
