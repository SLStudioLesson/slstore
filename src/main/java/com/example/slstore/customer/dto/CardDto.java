package com.example.slstore.customer.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CardDto {
    private String cardNumber;
    private LocalDate expiryDate;
    private BigDecimal limit;
    private BigDecimal used;

    public CardDto(String cardNumber, LocalDate expiryDate, BigDecimal limit, BigDecimal used) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.limit = limit;
        this.used = used;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public BigDecimal getUsed() {
        return used;
    }

    public long getDaysUntilExpiry() {
        LocalDate currentDate = LocalDate.now();
        return ChronoUnit.DAYS.between(currentDate, expiryDate);
    }

    public BigDecimal calculateRemaining() {
        return limit.subtract(used);
    }
}