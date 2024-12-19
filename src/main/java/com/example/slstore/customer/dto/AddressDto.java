package com.example.slstore.customer.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AddressDto {
    private String address;
    private LocalDate registeredDate;
    private BigDecimal baseFee;
    private BigDecimal additionalFee;
    
    public AddressDto(String address, LocalDate registeredDate, 
            BigDecimal baseFee, BigDecimal additionalFee) {
        this.address = address;
        this.registeredDate = registeredDate;
        this.baseFee = baseFee;
        this.additionalFee = additionalFee;
    }
    
    public String getAddress() {
        return address; 
    }

    public LocalDate getRegisteredDate() {
        return registeredDate;
    }
    
    public long getDaysSinceRegistration() {
        LocalDate currentDate = LocalDate.now();
        return ChronoUnit.DAYS.between(registeredDate, currentDate);
    }
    
    public BigDecimal calculateDeliveryFee() {
        return baseFee.add(additionalFee);
    }
}
