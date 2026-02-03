package com.urlShortner.urlShortner.service.impl;

import com.urlShortner.urlShortner.entity.UrlShortner;
import com.urlShortner.urlShortner.exception.Response;
import com.urlShortner.urlShortner.repository.UrlShortnerRepository;
import com.urlShortner.urlShortner.service.UrlShortnerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UrlShortnerServiceImpl implements UrlShortnerService {

    @Autowired
    UrlShortnerRepository urlShortnerRepository;

    @Override
    public String shortUrl(String url, HttpServletRequest httpServletRequest) {
        String baseUrl = httpServletRequest.getRequestURL().toString().replace(httpServletRequest.getRequestURI(), "");
        Optional<UrlShortner> urlShortnerOptional = urlShortnerRepository.findUrlShortnerByfullUrl(url);
        if (urlShortnerOptional.isPresent()) {
            UrlShortner urlShortner = urlShortnerOptional.get();
            if (!urlShortner.isExpired()) {
                return baseUrl+"/"+urlShortner.getUniqueKey();
            }
            urlShortnerRepository.delete(urlShortner);
        }
        String trimmedUrl = generateShortKey(url);
        String shortenUrl = baseUrl + "/" + trimmedUrl; //// instead of getting whole url finding base url of the application
        urlShortnerRepository.save(buildUrlShortner(url, trimmedUrl));
        return trimmedUrl;
    }

    @Override
    public String redirectUrl(String uniqueKey) {
        Optional<UrlShortner> urlShortnerOptional = urlShortnerRepository.findUrlShortnerByuniqueKey(uniqueKey);
        if (urlShortnerOptional.isEmpty()) {
            return "";
        }
        UrlShortner urlShortner = urlShortnerOptional.get();
//        if (urlShortner.isExpired()) {
//            return "expired Url";
//        }
        return urlShortner.getFullUrl();
    }


    private UrlShortner buildUrlShortner(String fullUrl, String shortKey) {
        return new UrlShortner(null, shortKey, fullUrl, new Date(), null, false);
    }

    private String generateShortKey(String url) {
        int hash = url.hashCode();  /// getting hash of the string
        return Integer.toHexString(hash).substring(0, 4); // Safer than Base64
    }
}
