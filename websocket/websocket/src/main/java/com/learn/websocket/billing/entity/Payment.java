package com.learn.websocket.billing.entity;

import com.learn.websocket.entity.BaseEntity;
import jakarta.persistence.Entity;

@Entity
public class Payment extends BaseEntity {

    private String transactionNo;

    private Long orderId;

    private Long amount;

    private String status;


}
