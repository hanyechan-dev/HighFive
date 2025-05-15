package com.jobPrize.repository.memToCom.proposal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.memToCom.Proposal;

public interface ProposalRepository extends JpaRepository<Proposal, Long>, ProposalRepositoryCustom {

}
