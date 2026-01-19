package com.commonPractice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CabBook {

    @Id
    private Long id;


    private String driverName;

    private boolean isBooked;
}
