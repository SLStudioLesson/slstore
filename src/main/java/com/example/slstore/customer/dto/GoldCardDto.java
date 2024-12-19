package com.example.slstore.customer.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GoldCardDto extends CardDto {
    public GoldCardDto(String cardNumber, LocalDate expiryDate, BigDecimal limit, BigDecimal used) {
        super(cardNumber, expiryDate, limit, used);
    }
}