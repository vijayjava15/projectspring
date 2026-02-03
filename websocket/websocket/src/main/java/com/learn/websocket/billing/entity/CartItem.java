package com.learn.websocket.billing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learn.websocket.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
public class CartItem extends BaseEntity {

  private String productName;

  private Long price;

  private Long quantity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cart_id", nullable = false)
  @JsonIgnore
  private Cart cart;


  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  public Cart getCart() {
    return cart;
  }

  public void setCart(Cart cart) {
    this.cart = cart;
  }
}
