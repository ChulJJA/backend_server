/*
 * File: SecurityConfig.java
 * Project: Moim Back-end
 * Desc: Spring Security configuration class.
 * Author: ChulJJA
 * Created: 09.01.2025.
 * Last Modified: 09.09.2025.
 */

package com.moim.config;

import com.moim.security.JwtAuthFilter;
import org.springframework.context.annotation.Bean; // Defines a Spring-managed bean.
import org.springframework.context.annotation.Configuration; // Marks this class as a source of Spring bean definitions.
import org.springframework.security.config.annotation.web.builders.HttpSecurity; // Configure web-based security for specific HTTP requests.
import org.springframework.security.web.SecurityFilterChain; // The object Spring Security uses to filter HTTP requests.
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter; // Filter for processing login (username/password) authentication requests
import lombok.RequiredArgsConstructor; // Generates constructor for final fields (dependency injection)

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
        http
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .cors(cors -> { })
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/ping",
                                         "/actuator/health",
                                         "/actuator/mappings",
                                         "/api/v1/auth/**")
                        .permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
