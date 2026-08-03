package com.ela.microservices.auth.controller;

import com.ela.microservices.auth.dto.CustomerResponse;
import com.ela.microservices.auth.entity.Customer;
import com.ela.microservices.auth.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @GetMapping
    public List<CustomerResponse> getCustomers() {
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public CustomerResponse getCustomer(@PathVariable Long id) {
        return customerService.getById(id);
    }
}