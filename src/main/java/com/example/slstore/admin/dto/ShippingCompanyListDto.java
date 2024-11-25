package com.example.slstore.admin.dto;

public class ShippingCompanyListDto {
    private Long id;
    private String name;
    private String contactPhone;
    private String deliveryDaysRange;
    private boolean isActive;
    
    public ShippingCompanyListDto() {
    }
    
    public ShippingCompanyListDto(Long id, String name, String contactPhone, String deliveryDaysRange,
            boolean isActive) {
        this.id = id;
        this.name = name;
        this.contactPhone = contactPhone;
        this.deliveryDaysRange = deliveryDaysRange;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getDeliveryDaysRange() {
        return deliveryDaysRange;
    }

    public void setDeliveryDaysRange(String deliveryDaysRange) {
        this.deliveryDaysRange = deliveryDaysRange;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    
}
