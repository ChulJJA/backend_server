/*
 * File: PostService.java
 * Project: Moim Back-end
 * Desc: Service for POST method.
 * Author: ChulJJA
 * Created: 09.07.2025.
 * Last Modified: 09.08.2025.
 */

package com.moim.service;

import com.moim.api.dto.CreatePostRequest;
import com.moim.api.dto.PostResponse;
import com.moim.domain.Board;
import com.moim.domain.Post;
import com.moim.domain.User;
import com.moim.repo.BoardRepo;
import com.moim.repo.PostRepo;
import com.moim.repo.UserRepo;
import com.moim.repo.ReactionRepo;
import com.moim.exception.NotFoundException;

import org.springframework.stereotype.Service; // Marks class as a Spring service component
import org.springframework.transaction.annotation.Transactional; // Declares transactional boundaries
import org.springframework.data.domain.Page; // Paginated result container
import org.springframework.data.domain.Pageable; // Pagination + sorting info for queries
import lombok.RequiredArgsConstructor; // Generates constructor for final fields (dependency injection)

@Service
@RequiredArgsConstructor
public class PostService {
    private final BoardRepo boardRepo;
    private final PostRepo postRepo;
    private final UserRepo userRepo;
    private final ReactionRepo reactionRepo;

    @Transactional
    public PostResponse create(Long boardId, Long authorId, CreatePostRequest req) {
        Board board = boardRepo.findById(boardId).orElseThrow(() -> new NotFoundException("Board"));
        User author = userRepo.findById(authorId).orElseThrow(() -> new NotFoundException("User"));
        Post p = new Post();
        p.setBoard(board);
        p.setAuthor(author);
        p.setTitle(req.title());
        p.setContent(req.content());
        Post saved = postRepo.save(p);
        return toResp(saved, 0);
    }

    @Transactional(readOnly = true)
    public Page<PostResponse> list(Long boardId, Pageable pageable) {
        return postRepo.findByBoardId(boardId, pageable)
                .map(p -> toResp(p, reactionRepo.countByPostIdAndType(p.getId(), "LIKE")));
    }

    private PostResponse toResp(Post p, long likeCount) {
        return new PostResponse(
                p.getId(), p.getTitle(), p.getContent(),
                p.getAuthor().getId(), p.getAuthor().getNickname(),
                p.getCreatedAt(), p.getUpdatedAt(),
                likeCount
        );
    }
}