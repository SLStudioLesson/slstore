package com.example.slstore.customer.dto;

import java.time.LocalDate;

public class FixedAmountCouponDto extends CouponDto {
    private int discountAmount;

    public FixedAmountCouponDto(Integer id, String code, String description,
            LocalDate validFrom, LocalDate validUntil, int discountAmount) {
        super(id, code, description, validFrom, validUntil);
        this.discountAmount = discountAmount;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public String getDiscountText() {
        return "割引額: " + discountAmount + "円";
    }
}