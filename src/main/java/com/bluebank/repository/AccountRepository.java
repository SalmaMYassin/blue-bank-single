package com.bluebank.repository;

import com.bluebank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByCustomerId(Long customerId);
}
