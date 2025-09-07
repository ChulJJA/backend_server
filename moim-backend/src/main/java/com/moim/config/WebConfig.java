/*
 * File: WebConfig.java
 * Project: Moim Back-end
 * Desc: Configures global CORS settings for the application.
 * Author: ChulJJA
 * Created: 09.01.2025.
 * Last Modified: 09.06.2025.
 */

package com.moim.config;

import org.springframework.context.annotation.Bean; // Defines a Spring-managed bean.
import org.springframework.context.annotation.Configuration; // Marks the class as a configuration component.
import org.springframework.web.cors.CorsConfiguration; // Holds settings for Cross-Origin Resource Sharing (CORS).
import org.springframework.web.cors.UrlBasedCorsConfigurationSource; // Maps CORS configuration to URL patterns.
import org.springframework.web.filter.CorsFilter; // Filter applied to requests to enforce CORS rules.

import java.util.List;

// Specifies HTTP Methods, headers
@Configuration
public class WebConfig {
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration cors = new CorsConfiguration();
        // port: 19006 - React Native Expo | 3000 - React dev sever | 8001 - Other front-end | * - Approve all
        cors.setAllowedOrigins(List.of("http://localhost:19006","http://localhost:3000","http://localhost:8081","*")); // dev only
        cors.setAllowedMethods(List.of("GET","POST","PUT","DELETE","PATCH","OPTIONS"));
        cors.setAllowedHeaders(List.of("Authorization","Content-Type","Accept"));
        cors.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Registers the CORS config for all endpoints /**
        source.registerCorsConfiguration("/**", cors);
        return new CorsFilter(source);
    }
}
