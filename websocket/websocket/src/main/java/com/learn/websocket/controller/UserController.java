package com.learn.websocket.controller;

import com.learn.websocket.entity.Employee;
import com.learn.websocket.reflection.GetCall;
import com.learn.websocket.service.EmployeeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;

@RestController
public class UserController {

    @Autowired
    EmployeeService employeeService;


    @Autowired
    GetCall getCall;

    @PostMapping("/employee")
    public Object saveEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }


    @GetMapping("/employee")
    Employee getEmployeeByEmail(@RequestParam String email) {

        return employeeService.findByEmail(email);
    }

    @GetMapping("/test")
    void test(HttpServletResponse response) throws IOException, InterruptedException {
      getCall.makeCall();
    }
}
