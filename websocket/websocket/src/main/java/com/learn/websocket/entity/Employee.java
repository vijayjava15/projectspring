package com.learn.websocket.entity;

import com.learn.websocket.model.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee extends BaseEntity {

    private String username;

    private String fullName;

    private String email;

    private String password;

    private String salt;

    @Enumerated(EnumType.STRING)
    private Role role;


    @CreatedDate
    private Date lastLoginDate;
}
