package com.example.slstore.common.entity;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.URL;

import com.example.slstore.admin.dto.ShippingCompanyDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "shipping_companies")
public class ShippingCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "配送業者名は必須です")
    @Size(max = 100, message = "配送業者名は100文字以内で入力してください")
    private String name;

    @URL(message = "追跡URLの形式が正しくありません")
    private String trackingUrlFormat;

    @Pattern(regexp = "^0\\d{1,4}-\\d{1,4}-\\d{4}$", message = "電話番号の形式が正しくありません")
    private String contactPhone;

    @NotNull(message = "最小配送日数は必須です")
    @Min(value = 1, message = "最小配送日数は1日以上である必要があります")
    @Max(value = 30, message = "最小配送日数は30日以下である必要があります")
    private Integer deliveryDaysMin;

    @NotNull(message = "最大配送日数は必須です")
    @Min(value = 1, message = "最大配送日数は1日以上である必要があります")
    @Max(value = 30, message = "最大配送日数は30日以下である必要があります")
    private Integer deliveryDaysMax;

    private boolean isActive;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ShippingCompany() {
    }

    public ShippingCompany(Long id, String name, String trackingUrlFormat, String contactPhone, Integer deliveryDaysMin,
            Integer deliveryDaysMax) {
        this.id = id;
        this.name = name;
        this.trackingUrlFormat = trackingUrlFormat;
        this.contactPhone = contactPhone;
        this.deliveryDaysMin = deliveryDaysMin;
        this.deliveryDaysMax = deliveryDaysMax;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        isActive = true;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
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

    public String getTrackingUrlFormat() {
        return trackingUrlFormat;
    }

    public void setTrackingUrlFormat(String trackingUrlFormat) {
        this.trackingUrlFormat = trackingUrlFormat;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Integer getDeliveryDaysMin() {
        return deliveryDaysMin;
    }

    public void setDeliveryDaysMin(Integer deliveryDaysMin) {
        this.deliveryDaysMin = deliveryDaysMin;
    }

    public Integer getDeliveryDaysMax() {
        return deliveryDaysMax;
    }

    public void setDeliveryDaysMax(Integer deliveryDaysMax) {
        this.deliveryDaysMax = deliveryDaysMax;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ShippingCompanyDto toDto() {
        return new ShippingCompanyDto(id, name, trackingUrlFormat, contactPhone, deliveryDaysMin, deliveryDaysMax, isActive);
    }

    public void updateFromDto(ShippingCompanyDto dto) {
        this.name = dto.getName();
        this.trackingUrlFormat = dto.getTrackingUrlFormat();
        this.contactPhone = dto.getContactPhone();
        this.deliveryDaysMin = dto.getDeliveryDaysMin();
        this.deliveryDaysMax = dto.getDeliveryDaysMax();
        this.isActive = dto.getIsActive();
    }

    
}