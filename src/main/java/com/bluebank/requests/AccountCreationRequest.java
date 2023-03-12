package com.bluebank.requests;

import com.bluebank.Enum.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class AccountCreationRequest {
    private AccountType type;
    private BigDecimal initialCredit;
    private Long customerId;
}
