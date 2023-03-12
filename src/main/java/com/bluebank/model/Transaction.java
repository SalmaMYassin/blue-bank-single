package com.bluebank.model;

import com.bluebank.Enum.TransactionType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Transaction {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime createdAt;
    private BigDecimal amount;
    private TransactionType type;

    @Nonnull
    @ManyToOne
    @JsonBackReference
    private Account account;

}
