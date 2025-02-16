package com.urlShortner.urlShortner.repository;

import com.urlShortner.urlShortner.entity.UrlShortner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlShortnerRepository extends JpaRepository<UrlShortner, Long> {


    Optional<UrlShortner> findUrlShortnerByfullUrl(String url);

    Optional<UrlShortner> findUrlShortnerByuniqueKey(String uniqueKey);
}
