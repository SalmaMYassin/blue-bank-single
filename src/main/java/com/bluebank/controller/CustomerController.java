package com.bluebank.controller;

import com.bluebank.model.Customer;
import com.bluebank.service.CustomerService;
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
    public void register(@RequestBody Customer customer) {
        log.info("New Customer Registration {}", customer);
        customerService.registerCustomer(customer);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity getCustomerData(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (Objects.isNull(customer))
            return new ResponseEntity("Customer does not exist", HttpStatus.NOT_FOUND);

        return new ResponseEntity(customer, HttpStatus.OK);

    }
}
