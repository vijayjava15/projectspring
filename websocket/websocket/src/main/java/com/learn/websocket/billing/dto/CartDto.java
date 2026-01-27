package com.learn.websocket.billing.dto;

import java.io.Serializable;
import java.util.List;

public class CartDto implements Serializable {

    private String userName;

    private List<CartItemDto> cartItems;

    private Long totalAmount;


    public String getUserName() {
        return userName;
    }

    public List<CartItemDto> getCartItems() {
        return cartItems;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setCartItems(List<CartItemDto> cartItems) {
        this.cartItems = cartItems;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
