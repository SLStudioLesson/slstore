package com.example.slstore.admin.dto;

import java.time.LocalDateTime;

public class ProductStockDto {
    private Long productId;
    private String productName;
    private String sku;
    private Integer currentQuantity;
    private LocalDateTime lastUpdatedAt;

    public ProductStockDto(Long id, String name, String sku, int quantity, LocalDateTime updatedAt) {
        this.productId = id;
        this.productName = name;
        this.sku = sku;
        this.currentQuantity = quantity;
        this.lastUpdatedAt = updatedAt;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getSku() {
        return sku;
    }

    public Integer getCurrentQuantity() {
        return currentQuantity;
    }

    public LocalDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }
}