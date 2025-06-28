package com.jobPrize.repository.member.member;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.member.Member;
import com.jobPrize.enumerate.EmbeddingStatus;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom{
	List<Member> findAllByEmbeddingStatus(EmbeddingStatus embeddingStatus);
}
