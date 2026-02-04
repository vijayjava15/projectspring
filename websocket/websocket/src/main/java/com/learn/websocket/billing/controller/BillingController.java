package com.learn.websocket.billing.controller;

import com.learn.websocket.billing.dto.CartDto;
import com.learn.websocket.billing.dto.CartItemDto;
import com.learn.websocket.billing.entity.CartItem;
import com.learn.websocket.billing.entity.Order;
import com.learn.websocket.billing.service.BillingService;
import com.learn.websocket.billing.entity.Product;
import com.learn.websocket.billing.utility.BillPdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/")
public class BillingController {


    @Autowired
    BillingService billingService;


    @Autowired
    BillPdfGenerator billPdfGenerator;

    @PostMapping("addProduct")
    public Object addProduct(@RequestBody Product product) {
      return  billingService.addProduct(product);
    }


    @GetMapping("getProduct")
    public Object getProduct() {
        return  billingService.getProds();
    }

    @PostMapping("cart/addProduct")
    public Object addItemInCart(@RequestBody CartDto cart) {
        return  billingService.addItemInCart(cart);
    }


    @PostMapping("cart/getProduct")
    public Object getItem(@RequestBody CartDto cart) {
        return  billingService.getItem(cart);
    }

    @PostMapping("cart/clear")
    public Object clearCart(@RequestBody CartDto cart) {
        return  billingService.clearCart(cart);
    }



    @PostMapping("order/create")
    public Object createOrder(@RequestBody CartDto cart) {
        Order order = billingService.createOrder(cart);
        byte[] pdf = billPdfGenerator.generateBill(order, order.getOrderItems());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=bill.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

}
