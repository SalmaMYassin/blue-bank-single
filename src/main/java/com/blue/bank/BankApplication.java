package com.blue.bank;

import com.blue.bank.Enum.AccountType;
import com.blue.bank.controller.AccountController;
import com.blue.bank.model.Account;
import com.blue.bank.model.Customer;
import com.blue.bank.requests.AccountCreationRequest;
import com.blue.bank.service.AccountService;
import com.blue.bank.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootApplication
public class BankApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class, args);
    }

    @Bean
    CommandLineRunner run(CustomerService customerService, AccountService accountService) {
        return args -> {
            Customer salma = new Customer(null, "Salma", "Yassin",
                    "salmamyassinn@gmail.com", null);
            Customer wael = new Customer(null, "Mohamed", "Wael",
                    "mwael8@outlook.com", null);
            customerService.registerCustomer(salma);
            customerService.registerCustomer(wael);

            AccountCreationRequest accountCreationRequest = new AccountCreationRequest(AccountType.SAVINGS,
                    BigDecimal.valueOf(300), customerService.getCustomerByEmail("salmamyassinn@gmail.com").getId());
            accountService.create(accountCreationRequest);
        };
    }
}
