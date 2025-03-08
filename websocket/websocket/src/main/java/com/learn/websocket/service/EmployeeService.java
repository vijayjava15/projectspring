package com.learn.websocket.service;

import com.learn.websocket.entity.Employee;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
    String save(Employee employee);

    Employee findByEmail(String email);
}
