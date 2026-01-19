package com.commonPractice.repository;

import com.commonPractice.entity.CabBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CabRepository extends JpaRepository<CabBook, Long> {


    Optional<CabBook> findCabByDriverName(String driverName);

}
