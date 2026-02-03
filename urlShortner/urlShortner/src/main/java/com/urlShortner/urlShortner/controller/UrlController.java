package com.urlShortner.urlShortner.controller;

import com.urlShortner.urlShortner.exception.Response;
import com.urlShortner.urlShortner.service.UrlShortnerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class UrlController {


    @Autowired
    UrlShortnerService urlShortnerService;


    @PostMapping(value = "/url")
    public Object shortnenUrl(@RequestBody String url, HttpServletRequest request) {
        return urlShortnerService.shortUrl(url, request);
    }


    @PostMapping("/get")
    public String redirectUrl(@RequestBody String url, HttpServletResponse response) throws IOException {
       String fullUrl =  urlShortnerService.redirectUrl(url);
       return fullUrl;
    }
}
