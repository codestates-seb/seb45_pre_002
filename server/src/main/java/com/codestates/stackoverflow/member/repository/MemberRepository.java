package com.codestates.stackoverflow.member.repository;

import com.codestates.stackoverflow.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
