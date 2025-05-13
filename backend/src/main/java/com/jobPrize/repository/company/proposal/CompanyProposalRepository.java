package com.jobPrize.repository.company.proposal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.memToCom.Proposal;

public interface CompanyProposalRepository extends JpaRepository<Proposal, Long>, CompanyProposalRepositoryCustom {

}
