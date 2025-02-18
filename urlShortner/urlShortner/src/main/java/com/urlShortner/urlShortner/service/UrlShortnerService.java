package com.urlShortner.urlShortner.service;

import com.urlShortner.urlShortner.exception.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public interface UrlShortnerService {

    Object shortUrl(String url, HttpServletRequest httpServletRequest);

    String redirectUrl(String uniqueKey);
}
