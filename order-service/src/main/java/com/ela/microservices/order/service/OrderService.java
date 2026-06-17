package com.ela.microservices.order.service;

import com.ela.microservices.order.dto.CustomerResponse;
import com.ela.microservices.order.dto.NotificationRequest;
import com.ela.microservices.order.dto.OrderResponse;
import com.ela.microservices.order.entity.Order;
import com.ela.microservices.order.exception.CustomerNotFoundException;
import com.ela.microservices.order.exception.OrderNotFoundException;
import com.ela.microservices.order.rabbitmq.OrderCreatedEvent;
import com.ela.microservices.order.rabbitmq.OrderEventProducer;
import com.ela.microservices.order.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final RestClient restClient;
    private final OrderEventProducer producer;

    public OrderResponse save(Order order) {

        try {
            restClient.get()
                    .uri("http://localhost:8081/customers/{id}",
                            order.getCustomerId())
                    .retrieve()
                    .body(CustomerResponse.class);

            Order save = repository.save(order);

//            restClient.post()
//                    .uri("http://localhost:8083/notifications")
//                    .body(new NotificationRequest(
//                            "Order Created Successfully"))
//                    .retrieve()
//                    .toBodilessEntity();
            producer.publish(
                    new OrderCreatedEvent(
                            save.getId(),
                            save.getCustomerId(),
                            save.getProductName()
                    ));
            return new OrderResponse(
                    save.getId(),
                    save.getCustomerId(),
                    save.getProductName(),
                    save.getAmount()
            );

        } catch (Exception e) {
            throw new CustomerNotFoundException("Customer not found");
        }
    }

    public List<OrderResponse> getAll() {
        return repository.findAll()
                .stream()
                .map((order)-> new OrderResponse(
                        order.getId(),
                        order.getCustomerId(),
                        order.getProductName(),
                        order.getAmount()
                )).toList();
    }

    public OrderResponse getById(Long id) {
        Order order = repository.findById(id).orElseThrow(
                () -> new OrderNotFoundException("Order Not Found")
        );
        return new OrderResponse(
                order.getId(),
                order.getCustomerId(),
                order.getProductName(),
                order.getAmount()
        );

    }
}
