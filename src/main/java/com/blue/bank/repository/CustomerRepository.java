package com.blue.bank.repository;

import com.blue.bank.model.Account;
import com.blue.bank.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Boolean existsByEmail(String email);
    Customer findByEmail(String email);
}
