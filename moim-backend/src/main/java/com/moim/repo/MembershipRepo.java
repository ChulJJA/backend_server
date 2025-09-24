package com.moim.repo;

import com.moim.domain.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MembershipRepo extends JpaRepository<Membership, Long> {
    Optional<Membership> findByUserIdAndCommunityId(Long userId, Long communityId);
    boolean existsByUserIdAndCommunityId(Long userId, Long communityId);
    List<Membership> findByUserId(Long userId);
}
