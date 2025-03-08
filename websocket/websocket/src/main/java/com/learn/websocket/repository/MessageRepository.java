package com.learn.websocket.repository;

import com.learn.websocket.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Employee,Long> {

}
