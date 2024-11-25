package com.example.slstore.admin.dto;

import java.time.LocalDateTime;

import com.example.slstore.common.entity.ShippingCompany;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ShippingCompanyDto {

    private Long id;

    @NotBlank(message = "配送業者名は必須です")
    @Size(min = 3, max = 100, message = "配送業者名は3文字以上100文字以内で入力してください")
    private String name;

    @Pattern(regexp = "^https?://.*[0-9]{6}$", message = "追跡URLには{tracking_number}のプレースホルダーを含める必要があります")
    private String trackingUrlFormat;

    @Pattern(regexp = "^0\\d{1,4}-\\d{1,4}-\\d{4}$", message = "電話番号の形式が正しくありません（例：03-1234-5678）")
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

    public ShippingCompanyDto() {
    }

    public ShippingCompanyDto(Long id,
            String name,
            String trackingUrlFormat,
            String contactPhone,
            Integer deliveryDaysMin,
            Integer deliveryDaysMax,
            boolean isActive) {
        this.id = id;
        this.name = name;
        this.trackingUrlFormat = trackingUrlFormat;
        this.contactPhone = contactPhone;
        this.deliveryDaysMin = deliveryDaysMin;
        this.deliveryDaysMax = deliveryDaysMax;
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

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
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

    /**
     * 一覧表示用のDTOに変換
     */
    public ShippingCompanyListDto toListDto() {
        return new ShippingCompanyListDto(
                this.id,
                this.name,
                this.contactPhone,
                String.format("%d-%d日", this.deliveryDaysMin, this.deliveryDaysMax),
                this.isActive);
    }

    public ShippingCompany toEntity() {
        return new ShippingCompany(this.id, this.name, this.trackingUrlFormat, this.contactPhone, this.deliveryDaysMin, this.deliveryDaysMax);
    }
}
