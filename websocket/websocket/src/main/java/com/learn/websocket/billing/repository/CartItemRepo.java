package com.learn.websocket.billing.repository;

import com.learn.websocket.billing.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {

}
