package com.example.slstore.customer.dto;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();
    private int totalAmount;

    public List<CartItem> getItems() {
        return items;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}
