/*
 * File: AuthController.java
 * Project: Moim Back-end
 * Desc:
 * Author: ChulJJA
 * Created: 09.09.2025.
 * Last Modified: 09.09.2025.
 */

// com.moim.api.AuthController.java
package com.moim.api;

import com.moim.api.dto.*;
import com.moim.domain.User;
import com.moim.repo.UserRepo;
import com.moim.security.JwtUtil;

import org.springframework.security.crypto.password.PasswordEncoder; // Interface for encoding and verifying passwords
import org.springframework.web.bind.annotation.*; // Spring MVC annotations for REST controllers
import lombok.RequiredArgsConstructor; // Generates constructor for final fields (dependency injection)

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwt;

    @PostMapping("/signup")
    public TokenResponse signup(@RequestBody SignupRequest req) {
        if (userRepo.findByEmail(req.email()).isPresent())
            throw new IllegalArgumentException("Email already exists");
        var u = new User();
        u.setEmail(req.email());
        u.setPasswordHash(encoder.encode(req.password()));
        u.setNickname(req.nickname());
        userRepo.save(u);
        return new TokenResponse(jwt.generate(u.getEmail()));
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest req) {
        var u = userRepo.findByEmail(req.email())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
        if (!encoder.matches(req.password(), u.getPasswordHash()))
            throw new IllegalArgumentException("Invalid credentials");
        return new TokenResponse(jwt.generate(u.getEmail()));
    }
}
