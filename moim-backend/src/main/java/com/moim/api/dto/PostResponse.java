/*
 * File: PostResponse.java
 * Project: Moim Back-end
 * Desc: DTO (Data Transfer Objects)
 * Author: ChulJJA
 * Created: 09.07.2025.
 * Last Modified: 09.07.2025.
 */

package com.moim.api.dto;

import jakarta.validation.constraints.NotBlank; // Ensures String is not null, empty, or whitespace-only.
import java.time.Instant; // Represents a timestamp (date-time in UTC, nanosecond precision)

public record PostResponse(
        Long id, String title, String content,
        Long authorId, String authorNickname,
        Instant createdAt, Instant updatedAt,
        long likeCount
) {}