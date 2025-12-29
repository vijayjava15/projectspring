package com.learn.websocket.entity;

import com.learn.websocket.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chat")
public class Message extends BaseEntity {

    private String messageContent;

    @ManyToOne()
    @JoinColumn(name = "user_id",referencedColumnName ="id" , nullable = false)
    private User user;
}
