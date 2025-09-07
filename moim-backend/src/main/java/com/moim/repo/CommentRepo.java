/*
 * File: CommentRepo.java
 * Project: Moim Back-end
 * Desc: Custom queries by method.
 * Author: ChulJJA
 * Created: 09.07.2025.
 * Last Modified: 09.07.2025.
 */

package com.moim.repo;

import com.moim.domain.Comment;
import java.util.Optional; // Container for an optional (nullable) value
import org.springframework.data.domain.Page; // Spring Data object for paginated query results
import org.springframework.data.domain.Pageable; // Pagination + sorting information for queries
import org.springframework.data.jpa.repository.JpaRepository; // Spring Data JPA base repository interface

public interface CommentRepo extends JpaRepository<Comment, Long> {
    Page<Comment> findByPostId(Long postId, Pageable pageable);
}