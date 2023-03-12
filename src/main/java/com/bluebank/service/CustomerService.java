package com.bluebank.service;

import com.bluebank.model.Customer;
import com.bluebank.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public record CustomerService(CustomerRepository customerRepository) {
    public void registerCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Boolean exists(Long id) {
        return customerRepository.existsById(id);
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }
}
