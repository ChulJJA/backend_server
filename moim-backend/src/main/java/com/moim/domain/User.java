/*
 * File: User.java
 * Project: Moim Back-end
 * Desc: JPA Entity (Mapping DB tables with Java class)
 * Author: ChulJJA
 * Created: 09.07.2025.
 * Last Modified: 09.09.2025.
 */

package com.moim.domain;

import jakarta.persistence.*; // JPA annotations for ORM (mapping Java classes to DB tables)
import java.time.Instant; // Represents a timestamp (date-time in UTC, nanosecond precision)
import lombok.Getter; // Lombok annotation to auto-generate getter methods
import lombok.Setter; // Lombok annotation to auto-generate setter methods

@Entity @Table(name="users")
@Getter @Setter
public class User {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
    @Column(nullable=false, unique=true) String email;
    @Column(nullable=false, name="password_hash") String passwordHash;
    @Column(nullable=false) String nickname;
    @Column(nullable=false, columnDefinition="timestamp default current_timestamp")
    Instant createdAt = Instant.now();
}