/*
 * File: SecurityController.java
 * Project: Moim Back-end
 * Desc: Spring Security configuration class.
 * Author: ChulJJA
 * Created: 09.01.2025.
 * Last Modified: 09.06.2025.
 */

package com.moim.config;

import org.springframework.context.annotation.Bean; // Defines a Spring-managed bean.
import org.springframework.context.annotation.Configuration; // Marks this class as a source of Spring bean definitions.
import org.springframework.security.config.annotation.web.builders.HttpSecurity; // Configure web-based security for specific HTTP requests.
import org.springframework.security.web.SecurityFilterChain; // The object Spring Security uses to filter HTTP requests.

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                /* Permit all requests */
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                /* Disable Login */
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable());
        return http.build();
    }
}
