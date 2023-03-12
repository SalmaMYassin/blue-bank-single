package com.blue.bank.service;


import com.blue.bank.model.Customer;
import com.blue.bank.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;


    @Test
    void createCustomer() {
        Customer customer = new Customer(null, "Salma", "Yassin",
                "salmamyassinn@gmail.com", null);
        customerService.registerCustomer(customer);

        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).save(customerArgumentCaptor.capture());

        Customer capturedCustomer = customerArgumentCaptor.getValue();
        assertThat(capturedCustomer).isEqualTo(customer);
    }

}
