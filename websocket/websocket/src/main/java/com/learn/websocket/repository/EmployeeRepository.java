package com.learn.websocket.repository;

import com.learn.websocket.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Employee findEmployeeByEmail(String email);
}
