package com.bluebank.service;

import com.bluebank.Enum.AccountType;
import com.bluebank.model.Account;
import com.bluebank.repository.AccountRepository;
import com.bluebank.requests.AccountCreationRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public record AccountService(AccountRepository accountRepository,
                             TransactionService transactionService,
                             CustomerService customerService) {

    public void create(AccountCreationRequest accountCreationRequest) {
        Account account = new Account();
        account.setType(accountCreationRequest.getType() == null ? AccountType.SAVINGS : accountCreationRequest.getType());
        account.setCustomer(customerService.getCustomerById(accountCreationRequest.getCustomerId()));
        account.setCreatedAt(LocalDateTime.now());
        accountRepository.save(account);
        if (accountCreationRequest.getInitialCredit().compareTo(BigDecimal.ZERO) > 0)
            deposit(account.getId(), accountCreationRequest.getInitialCredit());
    }

    public Boolean customerExists(Long customerId) {
        return customerService.exists(customerId);
    }

    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }

    public BigDecimal getTotalCredit(Long accountId) {
        return getAccountById(accountId).getBalance();
    }


    public Boolean hasEnoughCredit(Long accountId, BigDecimal amount) {
        return amount.compareTo(getTotalCredit(accountId)) < 0;
    }

    public List<Account> getCustomerAccounts(Long customerId) {
        return accountRepository.findAllByCustomerId(customerId);
    }

    public Boolean exists(Long id) {
        return accountRepository.existsById(id);
    }

    public BigDecimal getCredit(Long id) {
        return accountRepository.getReferenceById(id).getBalance();
    }

    public void withdraw(Long id, BigDecimal amount) {
        Account account = accountRepository.findById(id).orElse(null);
        transactionService.withdraw(id, amount);
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);
    }

    public void deposit(Long id, BigDecimal amount) {
        Account account = accountRepository.findById(id).orElse(null);
        transactionService.deposit(id, amount);
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
    }
}
