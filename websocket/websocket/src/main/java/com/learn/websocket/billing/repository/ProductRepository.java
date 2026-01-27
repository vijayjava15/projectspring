package com.learn.websocket.billing.repository;

import com.learn.websocket.billing.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
