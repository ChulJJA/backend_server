/*
 * File: CreatePostReuqst.java
 * Project: Moim Back-end
 * Desc: DTO (Data Transfer Objects)
 * Author: ChulJJA
 * Created: 09.07.2025.
 * Last Modified: 09.07.2025.
 */

package com.moim.api.dto;

import jakarta.validation.constraints.NotBlank; // Ensures String is not null, empty, or whitespace-only.

public record CreatePostRequest(
        @NotBlank String title,
        @NotBlank String content
) {}
