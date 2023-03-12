package com.blue.bank.Repository;

import com.blue.bank.BankApplication;
import com.blue.bank.model.Customer;
import com.blue.bank.repository.CustomerRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BankApplication.class)
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void findCustomerByEmailExistent() {
        String email = "email@domain.com";
        Customer customer = new Customer(null, "Salma", "Yassin",
                email, null);
        customerRepository.save(customer);

        Customer test = customerRepository.findByEmail(email);

        assertThat(test.getFirstName()).isEqualTo(customer.getFirstName());
        assertThat(test.getLastName()).isEqualTo(customer.getLastName());
    }

    @Test
    void findCustomerByEmailNonexistent() {
        String email = "email2@domain.com";
        Customer customer = new Customer(null, "Salma", "Yassin",
                email, null);
        customerRepository.save(customer);

        Customer test = customerRepository.findByEmail(email + "asdf");

        assertThat(test).isNull();
    }
}
