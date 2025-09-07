/*
 * File: ReactionRepo.java
 * Project: Moim Back-end
 * Desc: Custom queries by method.
 * Author: ChulJJA
 * Created: 09.07.2025.
 * Last Modified: 09.07.2025.
 */

package com.moim.repo;

import com.moim.domain.Reaction;
import org.springframework.data.jpa.repository.JpaRepository; // Spring Data JPA base repository interface

public interface ReactionRepo extends JpaRepository<Reaction, Long> {
    long countByPostIdAndType(Long postId, String type);
    boolean existsByPostIdAndUserIdAndType(Long postId, Long userId, String type);
}