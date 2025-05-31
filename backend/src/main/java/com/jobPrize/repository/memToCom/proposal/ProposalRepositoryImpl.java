package com.jobPrize.repository.memToCom.proposal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.memToCom.Proposal;
import com.jobPrize.entity.memToCom.QProposal;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public class ProposalRepositoryImpl implements ProposalRepositoryCustom{ 


	
	private final JPAQueryFactory queryFactory;

	@Override
	public Page<Proposal> findAllByCompanyId(Long id, Pageable pageable) {
		QProposal proposal = QProposal.proposal;
		
		List<Proposal> results = queryFactory
				.selectFrom(proposal)
				.join(proposal.company).fetchJoin()
				.where(proposal.company.id.eq(id))
				.orderBy(proposal.proposalDate.desc())
				.offset(pageable.getOffset())
			    .limit(pageable.getPageSize())
			    .fetch();
		
		return new PageImpl<Proposal>(results, pageable, countProposalsByCompanyId(id));
	}
	
	@Override
	public Page<Proposal> findAllByMemberId(Long id, Pageable pageable) {
		QProposal proposal = QProposal.proposal;
		
		List<Proposal> results = queryFactory
				.selectFrom(proposal)
				.join(proposal.member).fetchJoin()
				.where(proposal.member.id.eq(id))
				.orderBy(proposal.proposalDate.desc())
				.offset(pageable.getOffset())
			    .limit(pageable.getPageSize())
			    .fetch();
		
		return new PageImpl<Proposal>(results, pageable, countProposalsByMemberId(id));
	}

	@Override
	public Optional<Long> findMemberIdByProposalId(Long id) {
		QProposal proposal = QProposal.proposal;

		Long result = queryFactory
			.select(proposal.member.id)
			.from(proposal)
			.where(proposal.id.eq(id))
			.fetchOne();
		
		return Optional.ofNullable(result);
	}
	
	@Override
	public Optional<Long> findCompanyIdByProposalId(Long id) {
		QProposal proposal = QProposal.proposal;

		Long result = queryFactory
			.select(proposal.company.id)
			.from(proposal)
			.where(proposal.id.eq(id))
			.fetchOne();
		
		return Optional.ofNullable(result);
	}
	




	
	private long countProposalsByCompanyId(Long id) {
		QProposal proposal = QProposal.proposal;

	    return Optional.ofNullable(
	    	queryFactory
	        .select(proposal.count())
	        .from(proposal)
	        .where(proposal.company.id.eq(id))
	        .fetchOne())
	    	.orElse(0L);
	}
	private long countProposalsByMemberId(Long id) {
		QProposal proposal = QProposal.proposal;

	    return Optional.ofNullable(
	    	queryFactory
	        .select(proposal.count())
	        .from(proposal)
	        .where(proposal.member.id.eq(id))
	        .fetchOne())
	    	.orElse(0L);
	}

}