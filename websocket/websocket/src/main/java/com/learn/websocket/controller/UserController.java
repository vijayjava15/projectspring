package com.learn.websocket.controller;

import com.learn.websocket.entity.Employee;
import com.learn.websocket.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/employee")
    public Object saveEmployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }


    @GetMapping("/employee")
    Employee getEmployeeByEmail(@RequestParam String email){

      return employeeService.findByEmail(email);
    }
}
