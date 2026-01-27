package com.learn.websocket.billing.mapper;

import com.learn.websocket.billing.dto.CartItemDto;
import com.learn.websocket.billing.entity.Cart;
import com.learn.websocket.billing.entity.CartItem;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {

    @Autowired
    ModelMapper mapper;



    public CartItem mapToCartItem(CartItemDto cartItemDto){
        return mapper.map(cartItemDto, CartItem.class);
    }

    public CartItemDto mapToCartDto(CartItem cartItem){
        return mapper.map(cartItem, CartItemDto.class);
    }
}
