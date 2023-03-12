package com.blue.bank.service;

import com.blue.bank.model.Customer;
import com.blue.bank.repository.CustomerRepository;
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

    public Boolean exists(String email) {
        return customerRepository.existsByEmail(email);
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}