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
import com.moim.domain.Community;
import com.moim.domain.Membership;
import com.moim.domain.Role;
import com.moim.domain.User;
import com.moim.repo.CommunityRepo;
import com.moim.repo.MembershipRepo;
import com.moim.repo.RoleRepo;
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
    private final RoleRepo roleRepo;
    private final CommunityRepo communityRepo;
    private final MembershipRepo membershipRepo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwt;

    @PostMapping("/signup")
    public TokenResponse signup(@RequestBody SignupRequest req) {
        if (userRepo.findByEmail(req.email()).isPresent())
        {
            throw new IllegalArgumentException("Email already exists");
        }
        if (req.communitySlug() == null || req.communitySlug().isBlank()) {
            throw new IllegalArgumentException("communitySlug is required");
        }
        Community community = communityRepo.findBySlug(req.communitySlug())
                .orElseThrow(() -> new IllegalArgumentException("Unknown community: " + req.communitySlug()));

        Role userRole = roleRepo.findByCode("USER")
                .orElseThrow(() -> new IllegalStateException("Missing base role USER"));

        var u = new User();
        u.setEmail(req.email());
        u.setPasswordHash(encoder.encode(req.password()));
        u.setNickname(req.nickname());
        u.getRoles().add(userRole);
        u.setCurrentCommunity(community);
        userRepo.save(u);

        if (membershipRepo.existsByUserIdAndCommunityId(u.getId(), community.getId())) {
            Membership m = new Membership();
            m.setUser(u);
            m.setCommunity(community);
            m.setRole(userRole); // school-specific role at signup
            membershipRepo.save(m);
        }

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
