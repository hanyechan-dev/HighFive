package com.jobPrize.repository.common.user;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.common.QUser;
import com.jobPrize.entity.common.User;
import com.jobPrize.enumerate.ApprovalStatus;
import com.jobPrize.enumerate.UserType;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

	private final JPAQueryFactory queryFactory;
	
	@Override
	public Page<User> findAllWithSubEntityByUserType(UserType userType, Pageable pageable) {
		QUser user = QUser.user;
		
		List<User> results = null;
		if(userType == UserType.일반회원) {
			
			results = queryFactory
					.selectFrom(user)
					.join(user.member).fetchJoin()
					.orderBy(user.createdDate.desc())
					.offset(pageable.getOffset())
					.limit(pageable.getPageSize())
				    .fetch();
		}
		else if(userType == UserType.기업회원) {
			
			results = queryFactory
					.selectFrom(user)
					.join(user.company).fetchJoin()
					.orderBy(user.createdDate.desc())
					.offset(pageable.getOffset())
					.limit(pageable.getPageSize())
				    .fetch();
		}
		else if(userType == UserType.컨설턴트회원) {
			
			results = queryFactory
					.selectFrom(user)
					.join(user.consultant).fetchJoin()
					.orderBy(user.createdDate.desc())
					.offset(pageable.getOffset())
					.limit(pageable.getPageSize())
				    .fetch();
		}

		
		return new PageImpl<User> (results, pageable, countUserByUserType(userType));
	}
	
	@Override
	public Page<User> findAllWithSubEntityByUserTypeAndApprovalStatusIsWaiting(UserType userType, Pageable pageable) {
		QUser user = QUser.user;
		
		List<User> results = null;
		if(userType == UserType.기업회원) {
			
			results = queryFactory
					.selectFrom(user)
					.join(user.company).fetchJoin()
					.where(user.approvalStatus.eq(ApprovalStatus.WAITING))
					.orderBy(user.createdDate.desc())
					.offset(pageable.getOffset())
					.limit(pageable.getPageSize())
				    .fetch();
		}
		else if(userType == UserType.컨설턴트회원) {
			
			results = queryFactory
					.selectFrom(user)
					.join(user.consultant).fetchJoin()
					.where(user.approvalStatus.eq(ApprovalStatus.WAITING))
					.orderBy(user.createdDate.desc())
					.offset(pageable.getOffset())
					.limit(pageable.getPageSize())
				    .fetch();
		}

		
		return new PageImpl<User> (results, pageable, countUserByUserTypeAndApprovalStatusIsWaiting(userType));
	}
	
	@Override
	public List<User> findAllForDelete() {
		QUser user = QUser.user;
		
		List<User> results = queryFactory
				.selectFrom(user)
				.where(
						user.deletedDate.isNotNull(),
						user.deletedDate.before(LocalDate.now().minusYears(2))
						)
			    .fetch();
	
		return results;
	}
	
	
	
	
	
	private long countUserByUserType(UserType userType) {
		QUser user = QUser.user;

		return Optional.ofNullable( 
				queryFactory.select(user.count())
				.from(user)
				.where(user.type.eq(userType))
				.fetchOne()
				).orElse(0L);
	}
	
	private long countUserByUserTypeAndApprovalStatusIsWaiting(UserType userType) {
		QUser user = QUser.user;

		return Optional.ofNullable( 
				queryFactory.select(user.count())
				.from(user)
				.where(user.approvalStatus.eq(ApprovalStatus.WAITING))
				.where(user.type.eq(userType))
				.fetchOne()
				).orElse(0L);
	}





}
