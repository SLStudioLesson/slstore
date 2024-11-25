package com.example.slstore.admin.dto;

import java.time.LocalDateTime;

public class StockChangeLogDto {

    private Long productId;
    private String productName;
    private int previousQuantity;
    private int changedQuantity;
    private int currentQuantity;
    private String reason;
    private LocalDateTime createdAt;

    public StockChangeLogDto(Long productId, String productName, int previousQuantity, int changedQuantity, int currentQuantity, String reason, LocalDateTime createdAt) {
        this.productId = productId;
        this.productName = productName;
        this.previousQuantity = previousQuantity;
        this.changedQuantity = changedQuantity;
        this.currentQuantity = currentQuantity;
        this.reason = reason;
        this.createdAt = createdAt;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getPreviousQuantity() {
        return previousQuantity;
    }

    public int getChangedQuantity() {
        return changedQuantity;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public String getReason() {
        return reason;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    

}
