/*
 * File: Board.java
 * Project: Moim Back-end
 * Desc: JPA Entity (Mapping DB tables with Java class)
 * Author: ChulJJA
 * Created: 09.07.2025.
 * Last Modified: 09.07.2025.
 */

package com.moim.domain;

import jakarta.persistence.*; // JPA annotations for ORM (mapping Java classes to DB tables)
import lombok.Getter; // Lombok annotation to auto-generate getter methods
import lombok.Setter; // Lombok annotation to auto-generate setter methods

@Entity @Table(name="boards",
        uniqueConstraints=@UniqueConstraint(name="uq_board", columnNames={"community_id","slug"}))
@Getter @Setter
public class Board {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="community_id", nullable=false) Community community;
    @Column(nullable=false) String slug;
    @Column(nullable=false) String name;
}