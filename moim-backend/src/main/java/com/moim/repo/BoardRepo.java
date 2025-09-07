/*
 * File: BoardRepo.java
 * Project: Moim Back-end
 * Desc:
 * Author: ChulJJA
 * Created: 09.07.2025.
 * Last Modified: 09.07.2025.
 */

package com.moim.repo;

import com.moim.domain.Board;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepo extends JpaRepository<Board, Long> {
    Optional<Board> findByCommunityIdAndSlug(Long communityId, String slug);
    Page<Board> findByCommunityId(Long communityId, Pageable pageable);
}