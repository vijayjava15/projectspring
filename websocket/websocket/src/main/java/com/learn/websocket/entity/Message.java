package com.learn.websocket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chat")
public class Message extends BaseEntity {

    private String messageContent;

    @ManyToOne()
    @JoinColumn(name = "emp_id",referencedColumnName ="id" , nullable = false)
    private Employee employee;
}
