package com.urlShortner.urlShortner.controller;

import com.urlShortner.urlShortner.service.UrlShortnerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlController {


    @Autowired
    UrlShortnerService urlShortnerService;


    @GetMapping(value = "/")
    public String shortnenUrl(@RequestParam(value = "url") String url, HttpServletRequest request) {
        return urlShortnerService.shortUrl(url, request);
    }
}
