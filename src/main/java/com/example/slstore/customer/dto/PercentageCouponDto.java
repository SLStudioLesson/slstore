package com.example.slstore.customer.dto;

import java.time.LocalDate;

public class PercentageCouponDto extends CouponDto {
    private double discountPercentage;

    public PercentageCouponDto(Integer id, String code, String description,
            LocalDate validFrom, LocalDate validUntil, double discountPercentage) {
        super(id, code, description, validFrom, validUntil);
        this.discountPercentage = discountPercentage;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public String getDiscountText() {
        return "割引率: " + discountPercentage + "%";
    }
}