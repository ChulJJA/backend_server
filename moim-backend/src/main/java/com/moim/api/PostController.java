/*
 * File: PostController.java
 * Project: Moim Back-end
 * Desc: Maps HTTP requests to methods
 * Author: ChulJJA
 * Created: 09.07.2025.
 * Last Modified: 09.09.2025.
 */

package com.moim.api;

import com.moim.repo.CommunityRepo;
import com.moim.repo.BoardRepo;
import com.moim.repo.UserRepo;
import com.moim.service.PostService;
import com.moim.api.dto.CreatePostRequest;
import com.moim.api.dto.PostResponse;
import com.moim.exception.NotFoundException;

import org.springframework.web.bind.annotation.RestController; // Marks class as REST controller
import org.springframework.web.bind.annotation.RequestMapping; // Base path mapping for controller
import org.springframework.web.bind.annotation.PostMapping; // Maps HTTP POST requests
import org.springframework.web.bind.annotation.GetMapping; // Maps HTTP GET requests
import org.springframework.web.bind.annotation.PathVariable; // Binds URL path segments to params
import org.springframework.web.bind.annotation.RequestParam; // Binds query parameters to params
import org.springframework.web.bind.annotation.RequestBody; // Binds JSON body to an object
import org.springframework.data.domain.Page; // Page wrapper for paginated results
import org.springframework.data.domain.PageRequest; // Factory for Pageable (page + size + sort)
import org.springframework.data.domain.Sort; // Sorting specification for queries
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.validation.Valid; // Enables bean validation on method params/bodies
import lombok.RequiredArgsConstructor; // Generates constructor for final fields (great for DI)
import lombok.extern.slf4j.Slf4j; // Adds 'log' logger field (SLF4J)

@Slf4j
@RestController
@RequestMapping("/api/v1/communities/{slug}/boards/{boardSlug}/posts")
@RequiredArgsConstructor
public class PostController {
    private final CommunityRepo communityRepo;
    private final BoardRepo boardRepo;
    private final UserRepo userRepo; // replace with authenticated user later
    private final PostService postService;

    private Long resolveBoardId(String slug, String boardSlug) {
        Long communityId = communityRepo.findBySlug(slug)
                .orElseThrow(() -> new NotFoundException("Community")).getId();
        return boardRepo.findByCommunityIdAndSlug(communityId, boardSlug)
                .orElseThrow(() -> new NotFoundException("Board")).getId();
    }

    @PostMapping
    public PostResponse create(
            @PathVariable String slug,
            @PathVariable String boardSlug,
            @Valid @RequestBody CreatePostRequest req
    ) {
        Long boardId = resolveBoardId(slug, boardSlug);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = (auth != null) ? (String) auth.getPrincipal() : null;
        Long authorId = userRepo.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User")).getId();
        return postService.create(boardId, authorId, req);
    }

    @GetMapping
    public Page<PostResponse> list(
            @PathVariable String slug,
            @PathVariable String boardSlug,
            @RequestParam(defaultValue="0") int page,
            @RequestParam(defaultValue="20") int size
    ) {
        Long boardId = resolveBoardId(slug, boardSlug);
        return postService.list(boardId, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt")));
    }
}
