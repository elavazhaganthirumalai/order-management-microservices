package com.ela.microservices.auth.service;

import com.ela.microservices.auth.Exception.CustomerNotFoundException;
import com.ela.microservices.auth.dto.CustomerResponse;
import com.ela.microservices.auth.entity.Customer;
import com.ela.microservices.auth.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    public List<CustomerResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(customer -> new CustomerResponse(
                        customer.getId(),
                        customer.getName(),
                        customer.getEmail()
                ))
                .toList();
    }

    public CustomerResponse getById(Long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer not found"));

        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getEmail()
        );
    }
}