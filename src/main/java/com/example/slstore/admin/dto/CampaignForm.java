package com.example.slstore.admin.dto;

import java.time.LocalDate;

public class CampaignForm {
    private int id;

    private String name;

    private String description;

    private Integer minimunPurchase;
    
    private LocalDate validFrom;

    private LocalDate validTo;

    private Integer discountPercentage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMinimunPurchase() {
        return minimunPurchase;
    }

    public void setMinimunPurchase(Integer minimunPurchase) {
        this.minimunPurchase = minimunPurchase;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
