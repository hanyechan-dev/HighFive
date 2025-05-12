package com.jobPrize.repository.member.proposal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.memToCom.Proposal;

public interface MemberProposalRepository extends JpaRepository<Proposal, Long>, MemberProposalRepositoryCustom {

}
