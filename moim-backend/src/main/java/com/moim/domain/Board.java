/*
 * File: Board.java
 * Project: Moim Back-end
 * Desc: JPA Entity (Mapping DB tables with Java class)
 * Author: ChulJJA
 * Created: 09.07.2025.
 * Last Modified: 09.07.2025.
 */

package com.moim.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Table(name="boards",
        uniqueConstraints=@UniqueConstraint(name="uq_board", columnNames={"community_id","slug"}))
@Getter @Setter
public class Board {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="community_id", nullable=false) Community community;
    @Column(nullable=false) String slug;
    @Column(nullable=false) String name;
}