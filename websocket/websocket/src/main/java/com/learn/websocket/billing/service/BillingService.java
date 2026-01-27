package com.learn.websocket.billing.service;

import com.learn.websocket.billing.dto.CartDto;
import com.learn.websocket.billing.dto.CartItemDto;
import com.learn.websocket.billing.entity.Cart;
import com.learn.websocket.billing.entity.CartItem;
import com.learn.websocket.billing.entity.Product;
import com.learn.websocket.billing.mapper.CartItemMapper;
import com.learn.websocket.billing.repository.CartItemRepo;
import com.learn.websocket.billing.repository.CartRepo;
import com.learn.websocket.billing.repository.ProductRepository;
import com.learn.websocket.exception.ResponseUtility;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BillingService {

    private static final Logger log = LoggerFactory.getLogger(BillingService.class);
    @Autowired
    ProductRepository productRepository;


    @Autowired
    CartItemRepo cartItemRepo;


    @Autowired
    CartRepo cartRepo;

    @Autowired
    CartItemMapper cartItemMapper;


    public Object addProduct(Product product) {
        StringBuilder error = new StringBuilder();
        if (StringUtils.isBlank(product.getProductName())) {
            error.append("product name is null");
        }
        if (StringUtils.isBlank(product.getSellingPrice())) {
            error.append("selling price is null");
        }
        if (StringUtils.isBlank(product.getImageUrl())) {
            error.append("image is null");
        }
        if (error.length() > 0) {
            return ResponseUtility.BADREQUEST(null, error.toString());
        }
        return productRepository.save(product);
    }

    public Object getProds() {
        return productRepository.findAll();
    }


    public Object addItemInCart(CartDto cartDto) {

        //checking current cart for current user
        Optional<Cart> optionalCart = cartRepo.findByUserName(cartDto.getUserName());
        Cart cart = new Cart();
        if (optionalCart.isPresent()) {
            cart = optionalCart.get();
        }
        cart.setUserName(cartDto.getUserName());
        List<CartItem> cartItems = cartDto.getCartItems().stream().map(cartItemDto -> {
            return cartItemMapper.mapToCartItem(cartItemDto);
        }).toList();
        cartItemRepo.saveAll(cartItems);
        cart.setCartItems(cartItems);
        cart.setTotalAmount(cartDto.getTotalAmount());
        cartRepo.save(cart);
        return ResponseUtility.OK(cart, "product added to cart");
    }


    @Transactional
    public Object getItem(CartDto cartDto) {
        //checking current cart for current user

        try {
            Optional<Cart> optionalCart = cartRepo.findByUserName(cartDto.getUserName());
            Cart cart = new Cart();
            if (optionalCart.isPresent()) {
                cart = optionalCart.get();
                List<CartItemDto> cartItemsDtos = cart.getCartItems().stream().map(cartItemDto -> {
                    return  cartItemMapper.mapToCartDto(cartItemDto);
                }).toList();
                cartDto.setCartItems(cartItemsDtos);
                cartDto.setTotalAmount(cart.getTotalAmount());
                return ResponseUtility.OK(cart, "cart details");
            }
            return null;
        }catch (Exception e){
            log.error(e.toString());
        }

        return null;
    }
}
