/*
 * File: JwtUtil.java
 * Project: Moim Back-end
 * Desc:
 * Author: ChulJJA
 * Created: 09.09.2025.
 * Last Modified: 09.09.2025.
 */

package com.moim.security;

import org.springframework.beans.factory.annotation.Value; // Injects property values from config
import org.springframework.stereotype.Component; // Marks class as a Spring-managed bean
import io.jsonwebtoken.*; // JJWT core classes for building/parsing JWTs
import io.jsonwebtoken.security.Keys; // Utility for generating secure signing keys

import java.security.Key; // Represents cryptographic key
import java.time.Instant; // Timestamp (date-time in UTC)
import java.util.Date; // Legacy date/time representation

@Component
public class JwtUtil {
    private final Key key;
    private final String issuer;
    private final long accessMinutes;

    public JwtUtil(
            @Value("${app.jwt.secret}") String secret,
            @Value("${app.jwt.issuer}") String issuer,
            @Value("${app.jwt.access-token-minutes}") long accessMinutes) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.issuer = issuer;
        this.accessMinutes = accessMinutes;
    }

    public String generate(String subjectEmail) {
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(accessMinutes * 60);
        return Jwts.builder()
                .setSubject(subjectEmail)
                .setIssuer(issuer)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(exp))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String validateAndGetSubject(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
}
