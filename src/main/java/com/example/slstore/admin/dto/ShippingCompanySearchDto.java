package com.example.slstore.admin.dto;

public class ShippingCompanySearchDto {
    private String name;
    private Integer maxDeliveryDays;
    private Boolean isActive;

    public ShippingCompanySearchDto() {
    }

    public ShippingCompanySearchDto(String name, Integer maxDeliveryDays, Boolean isActive) {
        this.name = name;
        this.maxDeliveryDays = maxDeliveryDays;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMaxDeliveryDays() {
        return maxDeliveryDays;
    }

    public void setMaxDeliveryDays(Integer maxDeliveryDays) {
        this.maxDeliveryDays = maxDeliveryDays;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

}
