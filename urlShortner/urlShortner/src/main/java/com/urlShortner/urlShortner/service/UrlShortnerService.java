package com.urlShortner.urlShortner.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public interface UrlShortnerService {

    String shortUrl(String url, HttpServletRequest httpServletRequest);
}
