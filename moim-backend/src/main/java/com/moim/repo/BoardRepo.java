/*
 * File: BoardRepo.java
 * Project: Moim Back-end
 * Desc: Custom queries by method.
 * Author: ChulJJA
 * Created: 09.07.2025.
 * Last Modified: 09.07.2025.
 */

package com.moim.repo;

import com.moim.domain.Board;
import java.util.Optional; // Container for an optional (nullable) value
import org.springframework.data.domain.Page; // Spring Data object for paginated query results
import org.springframework.data.domain.Pageable; // Pagination + sorting information for queries
import org.springframework.data.jpa.repository.JpaRepository; // Spring Data JPA base repository interface

public interface BoardRepo extends JpaRepository<Board, Long> {
    Optional<Board> findByCommunityIdAndSlug(Long communityId, String slug);
    Page<Board> findByCommunityId(Long communityId, Pageable pageable);
}