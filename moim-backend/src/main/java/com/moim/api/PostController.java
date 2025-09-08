/*
 * File: PostController.java
 * Project: Moim Back-end
 * Desc: Maps HTTP requests to methods
 * Author: ChulJJA
 * Created: 09.07.2025.
 * Last Modified: 09.07.2025.
 */

package com.moim.api;

import lombok.extern.slf4j.Slf4j;

import com.moim.repo.CommunityRepo;
import com.moim.repo.BoardRepo;
import com.moim.repo.UserRepo;
import com.moim.service.PostService;
import com.moim.api.dto.CreatePostRequest;
import com.moim.api.dto.PostResponse;
import com.moim.exception.NotFoundException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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
        Long authorId = userRepo.findByEmail("cj.chuleee@gmail.com")
                .orElseThrow(() -> new NotFoundException("User")).getId(); // TODO: replace with auth principal
        return postService.create(boardId, authorId, req);
    }

    @GetMapping
    public Page<PostResponse> list(
            @PathVariable String slug,
            @PathVariable String boardSlug,
            @RequestParam(defaultValue="0") int page,
            @RequestParam(defaultValue="20") int size
    ) {
        log.info("hit");
        Long boardId = resolveBoardId(slug, boardSlug);
        return postService.list(boardId, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt")));
    }
}
