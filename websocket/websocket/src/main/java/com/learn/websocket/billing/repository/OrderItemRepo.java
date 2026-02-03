package com.learn.websocket.billing.repository;

import com.learn.websocket.billing.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepo extends JpaRepository<OrderItem,Long> {
}
