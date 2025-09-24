/*
 * File: User.java
 * Project: Moim Back-end
 * Desc: JPA Entity (Mapping DB tables with Java class)
 * Author: ChulJJA
 * Created: 09.07.2025.
 * Last Modified: 09.14.2025.
 */

package com.moim.domain;

import jakarta.persistence.*; // JPA annotations for ORM (mapping Java classes to DB tables)
import java.time.Instant; // Represents a timestamp (date-time in UTC, nanosecond precision)
import java.util.Set;           // Collection type for roles
import java.util.HashSet;       // Default implementation for Set
import lombok.Getter; // Lombok annotation to auto-generate getter methods
import lombok.Setter; // Lombok annotation to auto-generate setter methods
import lombok.NoArgsConstructor; // Auto-generates no-args constructor
import lombok.AllArgsConstructor; // Auto-generates all-args constructor

@Entity @Table(name="users")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
    @Column(nullable=false, unique=true) String email;
    @Column(nullable=false, name="password_hash") String passwordHash;
    @Column(nullable=false) String nickname;
//    @Column(nullable=false, name="current_community_id") Long currentCommunityID;
    @Column(nullable=false, columnDefinition="timestamp default current_timestamp")
    Instant createdAt = Instant.now();
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="current_community_id")
    private Community currentCommunity;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
}