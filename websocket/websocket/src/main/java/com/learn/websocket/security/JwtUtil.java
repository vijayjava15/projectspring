package com.learn.websocket.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

    // 256-bit key for HS256; Base64 avoids charset issues.
    private static final String SECRET_KEY_BASE64 = "vijayvijayrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr";
    private static final Key SIGNING_KEY =
            Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY_BASE64));

    private static final long EXPIRATION_MS = 15 * 60 * 1000; // 15 minutes

    public static String generateToken(String subject, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(SIGNING_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public static Claims validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SIGNING_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static String getUsername(String token) {
        return validateToken(token).getSubject();
    }

    public static boolean isTokenExpired(String token) {
        return validateToken(token)
                .getExpiration()
                .before(new Date());
    }
}
