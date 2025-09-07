/*
 * File: Post.java
 * Project: Moim Back-end
 * Desc: JPA Entity (Mapping DB tables with Java class)
 * Author: ChulJJA
 * Created: 09.07.2025.
 * Last Modified: 09.07.2025.
 */

package com.moim.domain;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

@Entity @Table(name="posts")
@Getter @Setter
public class Post {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="board_id", nullable=false) Board board;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="author_id", nullable=false) User author;
    @Column(nullable=false) String title;
    @Lob @Column(nullable=false) String content;
    @Column(nullable=false) Instant createdAt = Instant.now();
    Instant updatedAt;
}