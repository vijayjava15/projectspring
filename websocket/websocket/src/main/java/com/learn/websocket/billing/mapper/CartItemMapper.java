package com.learn.websocket.billing.mapper;

import com.learn.websocket.billing.dto.CartItemDto;
import com.learn.websocket.billing.entity.Cart;
import com.learn.websocket.billing.entity.CartItem;
import com.learn.websocket.billing.entity.OrderItem;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {

    @Autowired
    ModelMapper mapper;



    public CartItem mapToCartItem(CartItemDto cartItemDto, Cart cart){
        CartItem cartItem = mapper.map(cartItemDto, CartItem.class);
        cartItem.setCart(cart);
        return cartItem;
    }

    public CartItemDto mapToCartDto(CartItem cartItem){
        return mapper.map(cartItem, CartItemDto.class);
    }


//    public OrderItem mapToOrderItem(CartItem cartItem){
//        OrderItem orderItem = mapper.map(cartItem, OrderItem.class);
//        orderItem.setId(null);
//
//    }
}
