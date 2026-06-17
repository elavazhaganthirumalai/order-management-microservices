package com.ela.microservices.order.controller;

import com.ela.microservices.order.dto.OrderResponse;
import com.ela.microservices.order.entity.Order;
import com.ela.microservices.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderResponse createOrder(@RequestBody Order Order) {
        return orderService.save(Order);
    }

    @GetMapping
    public List<OrderResponse> getOrders() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public OrderResponse getOrder(@PathVariable Long id) {
        return orderService.getById(id);
    }
}
