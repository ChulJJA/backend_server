/*
 * File: Post.java
 * Project: Moim Back-end
 * Desc: JPA Entity (Mapping DB tables with Java class)
 * Author: ChulJJA
 * Created: 09.07.2025.
 * Last Modified: 09.07.2025.
 */

package com.moim.domain;

import jakarta.persistence.*; // JPA annotations for ORM (mapping Java classes to DB tables)
import java.time.Instant; // Represents a timestamp (date-time in UTC, nanosecond precision)
import lombok.Getter; // Lombok annotation to auto-generate getter methods
import lombok.Setter; // Lombok annotation to auto-generate setter methods

@Entity @Table(name="posts")
@Getter @Setter
public class Post {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="board_id", nullable=false) Board board;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="author_id", nullable=false) User author;
    @Column(nullable=false) String title;
    @Lob @Column(nullable = false, columnDefinition = "TEXT") String content;
    @Column(nullable=false) Instant createdAt = Instant.now();
    Instant updatedAt;
}