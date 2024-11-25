package com.example.slstore.customer.dto;

public class CartRequest {

    /** 追加したい商品ID */
    private int productId;
    /** 追加する数量 */
    private int quantity;

    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
