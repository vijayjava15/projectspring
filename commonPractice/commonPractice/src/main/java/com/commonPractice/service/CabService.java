package com.commonPractice.service;

import com.commonPractice.repository.CabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CabService {


    @Autowired
    CabRepository cabRepository;


    public void bookCab(){
        String driverName = "vijay";
        cabRepository.findCabByDriverName(driverName)
                .ifPresent(cabBook -> {
                    if (cabBook.isBooked()){
                        System.out.println("car is already booked");
                        return;
                    }
                    cabBook.setBooked(Boolean.TRUE);
                    cabRepository.save(cabBook);
                    System.out.println("cab booked for you");

                    System.out.println( " cab boooked for "         +           Thread.currentThread().getName());
                });

    }



}
