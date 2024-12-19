package com.example.slstore.customer.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class StandardCardDto extends CardDto {
    public StandardCardDto(String cardNumber, LocalDate expiryDate, BigDecimal limit, BigDecimal used) {
        super(cardNumber, expiryDate, limit, used);
    }
}
