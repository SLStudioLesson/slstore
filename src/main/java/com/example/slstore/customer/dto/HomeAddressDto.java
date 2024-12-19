package com.example.slstore.customer.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class HomeAddressDto extends AddressDto {
    public HomeAddressDto(String address, LocalDate registeredDate,
        BigDecimal baseFee, BigDecimal additionalFee) {
        super(address, registeredDate, baseFee, additionalFee);
    }
}
