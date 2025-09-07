/*
 * File: Reaction.java
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

@Entity @Table(name="reactions",
        uniqueConstraints=@UniqueConstraint(name="uq_react", columnNames={"post_id","user_id","type"}))
@Getter @Setter
public class Reaction {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="post_id", nullable=false) Post post;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="user_id", nullable=false) User user;
    @Column(nullable=false) String type; // "LIKE"
    @Column(nullable=false) Instant createdAt = Instant.now();
}