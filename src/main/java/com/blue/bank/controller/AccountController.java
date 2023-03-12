package com.blue.bank.controller;

import com.blue.bank.model.Account;
import com.blue.bank.requests.AccountCreationRequest;
import com.blue.bank.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
@Slf4j
public record AccountController(AccountService accountService) {
    @PostMapping
    public ResponseEntity create(@RequestBody AccountCreationRequest account) {
        if(accountService.customerExists(account.getCustomerId())){
            accountService.create(account);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        else return new ResponseEntity("Customer does not exist", HttpStatus.NOT_FOUND);

    }

    @GetMapping(path = "{customerId}")
    public List<Account> getCustomerAccounts(@PathVariable Long customerId) {
        return accountService.getCustomerAccounts(customerId);
    }

    @GetMapping(path = "balance/{id}")
    public ResponseEntity<BigDecimal> getCredit(@PathVariable Long id) {
        if (accountService.exists(id))
            return new ResponseEntity(accountService.getCredit(id), HttpStatus.OK);
        else
            return new ResponseEntity("Account does not exist", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/withdraw")
    public ResponseEntity withdraw(@RequestParam(name = "accountId") Long accountId,
                                   @RequestParam(name = "amount") BigDecimal amount) {

        if (accountService.exists(accountId)) {
            if (accountService.hasEnoughCredit(accountId, amount)) {
                accountService.withdraw(accountId, amount);
                log.info("Amount withdrawn from account");
                return new ResponseEntity(HttpStatus.OK);
            } else
                return new ResponseEntity("Not enough credit", HttpStatus.BAD_REQUEST);
        } else
            return new ResponseEntity("Account does not exist", HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/deposit")
    public ResponseEntity deposit(@RequestParam(name = "accountId") Long accountId,
                                  @RequestParam(name = "amount") BigDecimal amount) {
        if (accountService.exists(accountId)) {
            accountService.deposit(accountId, amount);
            log.info("Amount deposited to account");
            return new ResponseEntity(HttpStatus.OK);
        } else
            return new ResponseEntity("Account does not exist", HttpStatus.BAD_REQUEST);

    }

}
