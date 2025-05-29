package com.jobPrize.repository.member.member;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.member.Member;

public interface MemberRepository extends JpaRepository<Member, Long>,MemberRepositoryCustom{
}
