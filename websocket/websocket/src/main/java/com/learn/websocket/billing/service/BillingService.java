package com.learn.websocket.billing.service;

import com.learn.websocket.billing.dto.CartDto;
import com.learn.websocket.billing.dto.CartItemDto;
import com.learn.websocket.billing.entity.*;
import com.learn.websocket.billing.mapper.CartItemMapper;
import com.learn.websocket.billing.repository.*;
import com.learn.websocket.billing.utility.BillPdfGenerator;
import com.learn.websocket.exception.ResponseUtility;
import com.learn.websocket.notification.NotificationManager;
import com.learn.websocket.user.UserRepository;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    OrderItemRepo orderItemRepo;

    @Autowired
    NotificationManager notificationManager;

    @Autowired
    UserRepository userRepository;

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

        // checking current cart for current user
        Optional<Cart> optionalCart = cartRepo.findByUserName(cartDto.getUserName());
        Cart cart = new Cart();
        if (optionalCart.isPresent()) {
            List<CartItem> savedCartItems = new ArrayList<>();
            cart = optionalCart.get();

            Cart finalCart1 = cart;
            List<CartItem> newCartItems = cartDto.getCartItems().stream().map(cartItemDto -> {
                return cartItemMapper.mapToCartItem(cartItemDto, finalCart1);
            }).toList();

            Map<String, CartItem> existingCartItemMap = new HashMap<>();
            Map<String, Long> mapCount = new HashMap<>();

            for (CartItem existingCartItem : cart.getCartItems()) {
                existingCartItemMap.put(existingCartItem.getProductName(), existingCartItem);
                mapCount.put(existingCartItem.getProductName(),
                        existingCartItemMap.get(existingCartItem.getProductName()).getQuantity());

            }

            for (CartItem newCartItem : newCartItems) {
                if (existingCartItemMap.containsKey(newCartItem.getProductName())) {
                    mapCount.put(newCartItem.getProductName(),
                            existingCartItemMap.get(newCartItem.getProductName()).getQuantity() + 1);

                } else {
                    existingCartItemMap.put(newCartItem.getProductName(), newCartItem);
                    mapCount.put(newCartItem.getProductName(), 1l);
                }
            }

            for (CartItem mappedCartItem : existingCartItemMap.values()) {
                mappedCartItem.setQuantity(mapCount.get(mappedCartItem.getProductName()));
                CartItem savedCartItem = cartItemRepo.save(mappedCartItem);

                savedCartItems.add(savedCartItem);
            }
            long total = savedCartItems.stream()
                    .mapToLong(item -> item.getQuantity() * item.getPrice())
                    .sum();
            cart.setTotalAmount(total);
            cart.setCartItems(savedCartItems);
            cartRepo.save(cart);
            return ResponseUtility.OK(cart, "product added to cart");
        }
        cart.setUserName(cartDto.getUserName());
        Cart finalCart = cart;
        List<CartItem> cartItems = cartDto.getCartItems().stream().map(cartItemDto -> {
            CartItem cartItem = cartItemMapper.mapToCartItem(cartItemDto, finalCart);
            cartItem.setQuantity(1L);
            return cartItem;
        }).toList();

        cart.setCartItems(cartItems);
        long total = cartItems.stream()
                .mapToLong(item -> item.getQuantity() * item.getPrice())
                .sum();
        cart.setTotalAmount(total);
        cartRepo.save(cart);
        return ResponseUtility.OK(cart, "product added to cart");
    }

    @Transactional
    public Object getItem(CartDto cartDto) {
        // checking current cart for current user

        try {
            Optional<Cart> optionalCart = cartRepo.findByUserName(cartDto.getUserName());
            Cart cart = new Cart();
            if (optionalCart.isPresent()) {
                cart = optionalCart.get();
                List<CartItemDto> cartItemsDtos = cart.getCartItems().stream().map(cartItemDto -> {
                    return cartItemMapper.mapToCartDto(cartItemDto);
                }).toList();
                cartDto.setCartItems(cartItemsDtos);
                cartDto.setTotalAmount(cart.getTotalAmount());
                return ResponseUtility.OK(cart, "cart details");
            }
            return null;
        } catch (Exception e) {
            log.error(e.toString());
        }

        return null;
    }

    @Transactional
    public Object clearCart(CartDto cartDto) {
        Optional<Cart> optionalCart = cartRepo.findByUserName(cartDto.getUserName());
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.getCartItems().clear();
            cartRepo.delete(cart);
            return "";
        }
        return "";
    }

    @Transactional
    public Order  createOrder(CartDto cartDto) {

        try {
            Optional<Cart> optionalCart = cartRepo.findByUserName(cartDto.getUserName());
            Cart cart = optionalCart.get();
            List<CartItem> cartItems = cart.getCartItems();
            Order order = new Order();
            order.setUserName(cartDto.getUserName());
            order.setQty(cart.getQty());
            order.setTotalAmount(cart.getTotalAmount());
            List<OrderItem> orderItems = cartItems.stream().map(cartItem -> {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setPrice(cartItem.getPrice());
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setProductName(cartItem.getProductName());
                return orderItem;
            }).collect(Collectors.toList());
            order.setOrderItems(orderItems);

            Order savedOrder = orderRepo.save(order);
            clearCart(cartDto);
            return savedOrder;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally{
           notificationManager.notifyAll(userRepository.findByUsername(cartDto.getUserName()).get(), "thank you for purchasing from our store");
        }
       return null;
    }
}
