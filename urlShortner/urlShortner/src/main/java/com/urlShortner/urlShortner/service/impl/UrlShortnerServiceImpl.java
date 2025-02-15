package com.urlShortner.urlShortner.service.impl;

import com.urlShortner.urlShortner.entity.UrlShortner;
import com.urlShortner.urlShortner.repository.UrlShortnerRepository;
import com.urlShortner.urlShortner.service.UrlShortnerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Service
public class UrlShortnerServiceImpl implements UrlShortnerService {

    @Autowired
    UrlShortnerRepository urlShortnerRepository;

    @Override
    public String shortUrl(String url, HttpServletRequest httpServletRequest) {
        Optional<UrlShortner> urlShortnerOptional = urlShortnerRepository.findUrlShortnerByfullUrl(url);
        if (urlShortnerOptional.isPresent()) {
            UrlShortner urlShortner = urlShortnerOptional.get();
            if (!urlShortner.isExpired()) {
                return urlShortner.getUniqueKey();
            }
            urlShortnerRepository.delete(urlShortner);
        }
        var urlBytes = url.getBytes();
        String trimmedUrl = Base64.getEncoder().encodeToString(urlBytes).substring(0, 8);
        String shortenUrl = httpServletRequest.getRequestURL() + "/" + trimmedUrl;
        urlShortnerRepository.save(buildUrlShortner(url, shortenUrl));
        return shortenUrl;
    }


    private UrlShortner buildUrlShortner(String fullUrl, String shortKey) {
        return new UrlShortner(null, shortKey, fullUrl, new Date(), null, false);
    }
}
