package com.jobPrize.repository.member.proposal;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.memToCom.Proposal;
import com.jobPrize.entity.memToCom.QProposal;
import com.jobPrize.entity.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberProposalRepositoryImpl implements MemberProposalRepositoryCustom{
	
	private final JPAQueryFactory queryFactory;

	@Override
	public Page<Proposal> findProposalsByMemberId(Long id, Pageable pageable) {
		QProposal proposal = QProposal.proposal;
		QMember member = QMember.member;
		
		List<Proposal> results = queryFactory
				.selectFrom(proposal)
				.where(proposal.member.id.eq(id))
				.orderBy(proposal.proposalDate.desc())
				.offset(pageable.getOffset())
			    .limit(pageable.getPageSize())
			    .fetch();
		
		return new PageImpl<Proposal>(results, pageable, countProposalsById(id));
	}
	
	public long countProposalsById(Long id) {
		QProposal proposal = QProposal.proposal;

	    return queryFactory
	        .select(proposal.count())
	        .from(proposal)
	        .where(proposal.member.id.eq(id))
	        .fetchOne();
	}

}
