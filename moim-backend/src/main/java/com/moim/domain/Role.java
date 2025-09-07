/*
 * File: Role.java
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

@Entity @Table(name="roles")
@Getter @Setter
public class Role {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
    @Column(nullable=false, unique=true) String code;
    @Column(nullable=false) String name;
}