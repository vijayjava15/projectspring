package com.urlShortner.urlShortner.controller;

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


    @GetMapping(value = "/")
    public String shortnenUrl(@RequestParam(value = "url") String url, HttpServletRequest request) {
        return urlShortnerService.shortUrl(url, request);
    }package com.urlShortner.urlShortner.controller;

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


        @GetMapping(value = "/")
        public String shortnenUrl(@RequestParam(value = "url") String url, HttpServletRequest request) {
            return urlShortnerService.shortUrl(url, request);
        }


        @GetMapping("/{url}")
        public void redirectUrl(@PathVariable(value = "url") String url, HttpServletResponse response) throws IOException {
            String fullUrl =  urlShortnerService.redirectUrl(url);
            response.sendRedirect(fullUrl);
        }
    }



    @GetMapping("/{url}")
    public void redirectUrl(@PathVariable(value = "url") String url, HttpServletResponse response) throws IOException {
       String fullUrl =  urlShortnerService.redirectUrl(url);
       response.sendRedirect(fullUrl);
    }
}
