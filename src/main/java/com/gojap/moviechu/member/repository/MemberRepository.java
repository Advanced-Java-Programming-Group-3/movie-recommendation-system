package com.gojap.moviechu.member.repository;

import com.gojap.moviechu.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findBySocialId(String socialId);
    Optional<Member> findByRefreshToken(String refreshToken);
}
