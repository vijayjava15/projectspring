package com.learn.websocket.billing.repository;

import com.learn.websocket.billing.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {

    @Modifying
    @Transactional
    @Query("delete from CartItem c where c.cart.id = :cartId")
    void deleteByCartId(@Param("cartId") Long cartId);
}
