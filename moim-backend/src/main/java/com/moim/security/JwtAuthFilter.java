/*
 * File: JwtAuthFilter.java
 * Project: Moim Back-end
 * Desc:
 * Author: ChulJJA
 * Created: 09.09.2025.
 * Last Modified: 09.09.2025.
 */

package com.moim.security;

import com.moim.repo.UserRepo;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; // Authentication object with username, password, and authorities
import org.springframework.security.core.authority.SimpleGrantedAuthority; // Represents a simple user role/authority
import org.springframework.security.core.context.SecurityContextHolder; // Holds security context for the current thread
import org.springframework.stereotype.Component; // Marks class as a Spring-managed bean
import org.springframework.web.filter.OncePerRequestFilter; // Ensures filter runs once per request
import jakarta.servlet.FilterChain; // Controls the chain of servlet filters
import jakarta.servlet.http.HttpServletRequest; // Represents the incoming HTTP request
import jakarta.servlet.http.HttpServletResponse; // Represents the outgoing HTTP response
import lombok.RequiredArgsConstructor;  // Generates constructor for final fields (dependency injection)
import java.util.List; // Ordered collection interface that allows duplicates

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtil jwt;
    private final UserRepo userRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws java.io.IOException, jakarta.servlet.ServletException {
        String auth = req.getHeader("Authorization");
        if (auth != null && auth.startsWith("Bearer ")) {
            String token = auth.substring(7);
            try {
                String email = jwt.validateAndGetSubject(token);
                var user = userRepo.findByEmail(email);
                if (user.isPresent()) {
                    var authToken = new UsernamePasswordAuthenticationToken(
                            email, null, List.of(new SimpleGrantedAuthority("ROLE_USER")));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (Exception ignored) { /* invalid token => unauthenticated */ }
        }
        chain.doFilter(req, res);
    }
}
