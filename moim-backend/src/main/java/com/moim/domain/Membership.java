/*
 * File: Membership.java
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

@Entity @Table(name="memberships",
        uniqueConstraints=@UniqueConstraint(name="uq_member", columnNames={"user_id","community_id"}))
@Getter @Setter
public class Membership {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="user_id", nullable=false) User user;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="community_id", nullable=false) Community community;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="role_id", nullable=false) Role role;
    @Column(nullable=false, columnDefinition="timestamp default current_timestamp")
    Instant joinedAt = Instant.now();
}