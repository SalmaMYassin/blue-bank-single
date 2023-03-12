package com.bluebank.service;

import com.bluebank.Enum.TransactionType;
import com.bluebank.model.Transaction;
import com.bluebank.repository.AccountRepository;
import com.bluebank.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Slf4j
public record TransactionService(AccountRepository accountRepository,
                                 TransactionRepository transactionRepository) {

    public void deposit(Long accountId, BigDecimal amount) {
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.DEPOSIT);
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setAccount(accountRepository.findById(accountId).orElse(null));
        transaction.setAmount(amount);

        log.info("deposit transaction is being saved");
        transactionRepository.save(transaction);

    }

    public void withdraw(Long accountId, BigDecimal amount) {
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.WITHDRAW);
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setAccount(accountRepository.findById(accountId).orElse(null));
        transaction.setAmount(amount);

        log.info("withdraw transaction is being saved");

        transactionRepository.save(transaction);

    }


    public Transaction getById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public Page<Transaction> getAllByAccountId(Long accountId, int page, int size) {
        return transactionRepository.findAllByAccountId(accountId, PageRequest.of(page, size));
    }



    public Boolean accountExists(Long accountId) {
        return accountRepository.existsById(accountId);
    }
}
