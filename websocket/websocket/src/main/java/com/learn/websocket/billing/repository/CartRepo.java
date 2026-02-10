package com.learn.websocket.billing.repository;

import com.learn.websocket.billing.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepo extends JpaRepository<Cart,Long > {

    Optional<Cart> findByUserName(String userName);



}
