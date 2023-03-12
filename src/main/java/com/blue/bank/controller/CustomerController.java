package com.blue.bank.controller;

import com.blue.bank.model.Customer;
import com.blue.bank.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/api/v1/customer")
public record CustomerController(CustomerService customerService) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity register(@RequestBody Customer customer) {
        log.info("New Customer Registration {}", customer);
        if (!customerService.exists(customer.getEmail())){
            customerService.registerCustomer(customer);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        else
            return new ResponseEntity("Email already exists", HttpStatus.UNPROCESSABLE_ENTITY);

    }

    @GetMapping
    public ResponseEntity getCustomerData(@RequestParam(name = "email") String email) {
        Customer customer = customerService.getCustomerByEmail(email);
        if (Objects.isNull(customer))
            return new ResponseEntity("Customer does not exist", HttpStatus.NOT_FOUND);

        return new ResponseEntity(customer, HttpStatus.OK);

    }

    @GetMapping(path = "{id}")
    public ResponseEntity getCustomerData(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (Objects.isNull(customer))
            return new ResponseEntity("Customer does not exist", HttpStatus.NOT_FOUND);

        return new ResponseEntity(customer, HttpStatus.OK);

    }
}
