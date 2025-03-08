package com.learn.websocket.service.impl;

import com.learn.websocket.entity.Employee;
import com.learn.websocket.repository.EmployeeRepository;
import com.learn.websocket.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public String save(Employee employee) {
      employeeRepository.save(employee);
      return "employee saved sucessFully";
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeRepository.findEmployeeByEmail(email);
    }
}
