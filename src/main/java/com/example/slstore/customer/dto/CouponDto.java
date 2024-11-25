package com.example.slstore.customer.dto;

import java.time.LocalDate;

public class CouponDto {
    private Integer id;
    private String code;
    private String description;
    private LocalDate validFrom;
    private LocalDate validUntil;

    public String getDiscountText() {
        return "割引の表示を行います。";
    }

    // コンストラクタ
    public CouponDto(Integer id, String code, String description,
            LocalDate validFrom, LocalDate validUntil) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
    }

    // アクセサ
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDate validUntil) {
        this.validUntil = validUntil;
    }
}
