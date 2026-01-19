package com.commonPractice.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderNo;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList;
}
