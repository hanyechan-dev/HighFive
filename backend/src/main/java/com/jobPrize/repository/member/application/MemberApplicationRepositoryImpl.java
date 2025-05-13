package com.jobPrize.repository.member.application;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.memToCom.Application;
import com.jobPrize.entity.memToCom.QAppDocument;
import com.jobPrize.entity.memToCom.QApplication;
import com.jobPrize.entity.memToCom.QPass;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberApplicationRepositoryImpl implements MemberApplicationRepositoryCustom{
	
	private final JPAQueryFactory queryFactory;
	
	@Override
	public Page<Application> findAllByMemberId(Long id, Pageable pageable) {
		QApplication application = QApplication.application;
		
		List<Application> results = queryFactory
				.selectFrom(application)
				.where(application.member.id.eq(id))
				.orderBy(application.createdDate.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
			    .fetch();
		
		
		return new PageImpl<Application>(results,pageable, countApplicationsByMemberId(id));
	}
	
	@Override
	public Optional<Application> findByApplicationId(Long id) {
		QApplication application = QApplication.application;
		QPass pass = QPass.pass;
		QAppDocument appDocument = QAppDocument.appDocument;

		
		Application result = queryFactory
				.selectFrom(application)
				.leftJoin(application.appDocument, appDocument).fetchJoin()
				.leftJoin(application.pass, pass).fetchJoin()
				.where(application.id.eq(id))
				.distinct()
				.fetchOne();
				
				
		return Optional.ofNullable(result);
	}
	
	public long countApplicationsByMemberId(Long id) {
		QApplication application = QApplication.application;

	    return queryFactory
	        .select(application.count())
	        .from(application)
	        .where(application.member.id.eq(id))
	        .fetchOne();
	}

}



