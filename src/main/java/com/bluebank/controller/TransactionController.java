package com.bluebank.controller;

import com.bluebank.model.Transaction;
import com.bluebank.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/transaction")
@Slf4j
public record TransactionController(TransactionService transactionService) {


    @GetMapping(path = "{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        Transaction transaction = transactionService.getById(id);
        return !Objects.isNull(transaction) ?
                new ResponseEntity(transaction, HttpStatus.OK) :
                new ResponseEntity("Transaction does not exist", HttpStatus.NOT_FOUND);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Transaction> getAll(@RequestParam(name = "accountId") Long accountId,
                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "size", defaultValue = "4") int size) {
        return transactionService.getAllByAccountId(accountId, page, size);
    }
}
