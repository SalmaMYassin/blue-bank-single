package com.blue.bank;

import com.blue.bank.model.Customer;
import com.blue.bank.service.AccountService;
import com.blue.bank.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

	@Bean
	CommandLineRunner run(CustomerService customerService, AccountService accountService) {
		return args -> {
			Customer todoUser = new TodoUser(null, "Salma Yassin", "salma", "salma",null);
			TodoUser todoUser2 = new TodoUser(null, "Mohamed Wael", "wael", "wael",null);
			todoUserService.save(todoUser);
			todoUserService.save(todoUser2);
		};
	}
}
