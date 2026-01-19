package com.commonPractice.controller;

import com.commonPractice.service.CabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CabController {

    @Autowired
    CabService cabService;


    @GetMapping("/book")
    public void book(){

        cabService.bookCab();

    }
}
