package com.jobPrize.repository.company.member;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.member.Member;

public interface CompanyMemberRepository extends JpaRepository<Member, Long>, CompanyMemberRepositoryCustom{

}
