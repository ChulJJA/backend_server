/*
 * File: SecurityBeans.java
 * Project: Moim Back-end
 * Desc:
 * Author: ChulJJA
 * Created: 09.09.2025.
 * Last Modified: 09.09.2025.
 */

package com.moim.config;

import org.springframework.context.annotation.Bean; // Defines a Spring-managed bean.
import org.springframework.context.annotation.Configuration; // Marks this class as a source of Spring bean definitions.
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Implementation of PasswordEncoder using BCrypt hashing
import org.springframework.security.crypto.password.PasswordEncoder; // Interface for encoding and verifying passwords

@Configuration
public class SecurityBeans {
    @Bean public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
}