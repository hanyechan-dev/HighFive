package com.jobPrize.repository.common.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jobPrize.dto.admin.service.CountByDayDto;
import com.jobPrize.dto.admin.service.CountByMonthDto;
import com.jobPrize.dto.admin.service.SubsCountDto;
import com.jobPrize.dto.admin.service.UserCountDto;
import com.jobPrize.entity.common.QUser;
import com.jobPrize.enumerate.UserType;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ServiceRepositoryImpl implements ServiceRepositoryCustom {
	private final JPAQueryFactory queryFactory;
	
	// 지정된 기간 내 발생한 회원가입, 회원탈퇴 수를 사용자 유형에 따라 날짜(day)별로 조회
	@Override
	public List<CountByDayDto> countSignUpAndWithDrawalByDay(int days, UserType userType) {
	    QUser user = QUser.user;
	    LocalDate endDate = LocalDate.now();
	    LocalDate startDate = endDate.minusDays(days);

	    // 회원가입 집계
	    List<Tuple> signUpTuples = queryFactory
	        .select(user.createdDate, user.count())
	        .from(user)
	        .where(
	            user.type.eq(userType)
	            .and(user.createdDate.between(startDate, endDate))
	        )
	        .groupBy(user.createdDate)
	        .fetch();

	    // 회원탈퇴 집계
	    List<Tuple> withdrawTuples = queryFactory
	        .select(user.deletedDate, user.count())
	        .from(user)
	        .where(
	            user.type.eq(userType)
	            .and(user.deletedDate.isNotNull())
	            .and(user.deletedDate.between(startDate, endDate))
	        )
	        .groupBy(user.deletedDate)
	        .fetch();
	        
	    // Map<날짜, DTO>로 만들어서 각각의 결과를 병합
	    Map<LocalDate, CountByDayDto> resultMap = new HashMap<>();
	    
	    // 회원가입 건수 반영
	    for (Tuple tuple : signUpTuples) {
	        LocalDate date = tuple.get(user.createdDate);
	        Long count = tuple.get(user.count());
	        resultMap.put(date, new CountByDayDto(date, count, 0L));
	    }
	    
	    // 회원탈퇴 건수 반영
	    for (Tuple tuple : withdrawTuples) {
	        LocalDate date = tuple.get(user.deletedDate);
	        Long count = tuple.get(user.count());
	        CountByDayDto dto = resultMap.get(date);
	        if (dto == null) {
	            resultMap.put(date, new CountByDayDto(date, 0L, count));
	        } else {
	            dto.setWithdraws(dto.getWithdraws() + count);
	        }
	    }
	    
	    // Map의 결과를 List로 변환하고, 날짜순으로 정렬
	    List<CountByDayDto> results = new ArrayList<>(resultMap.values());
	    results.sort(Comparator.comparing(CountByDayDto::getDate));
	    
	    return results;
	}
	
	
	// 지정된 기간 내 발생한 회원가입, 회원탈퇴 수를 사용자 유형에 따라 월(month)별로 조회
	@Override
	public List<CountByMonthDto> countSignUpAndWithDrawalByMonth(int months, UserType userType) {
	    QUser user = QUser.user;
	    LocalDate now = LocalDate.now();
	    LocalDate startDate = now.minusMonths(months);

	    // DB에서는 일(day) 단위로 그룹핑하여 데이터 조회
	    List<Tuple> signUpTuples = queryFactory
	        .select(user.createdDate, user.count())
	        .from(user)
	        .where(
	            user.type.eq(userType)
	            .and(user.createdDate.between(startDate, now))
	        )
	        .groupBy(user.createdDate)
	        .fetch();

	    List<Tuple> withdrawTuples = queryFactory
	        .select(user.deletedDate, user.count())
	        .from(user)
	        .where(
	            user.type.eq(userType)
	            .and(user.deletedDate.isNotNull())
	            .and(user.deletedDate.between(startDate, now))
	        )
	        .groupBy(user.deletedDate)
	        .fetch();

	    // 월별 결과를 저장할 Map
	    Map<Integer, CountByMonthDto> monthlyResultMap = new HashMap<>();

	    // 회원가입 집계: 각 Tuple에서 날짜로부터 월 값을 추출 후 누적
	    for (Tuple tuple : signUpTuples) {
	        LocalDate createdDate = tuple.get(user.createdDate);
	        int monthKey = createdDate.getMonthValue();  // 예: 1월이면 1, 2월이면 2, 등
	        Long count = tuple.get(user.count());

	        CountByMonthDto dto = monthlyResultMap.get(monthKey);
	        if (dto == null) {
	            monthlyResultMap.put(monthKey, new CountByMonthDto(monthKey, count, 0L));
	        } else {
	            dto.setSignUps(dto.getSignUps() + count);
	        }
	    }

	    // 회원탈퇴 집계: 각 Tuple에서 날짜로부터 월 값을 추출 후 누적
	    for (Tuple tuple : withdrawTuples) {
	        LocalDate deletedDate = tuple.get(user.deletedDate);
	        int monthKey = deletedDate.getMonthValue();
	        Long count = tuple.get(user.count());

	        CountByMonthDto dto = monthlyResultMap.get(monthKey);
	        if (dto == null) {
	            monthlyResultMap.put(monthKey, new CountByMonthDto(monthKey, 0L, count));
	        } else {
	            dto.setWithdraws(dto.getWithdraws() + count);
	        }
	    }

	    // Map의 값들을 List로 변환 후, 월(숫자) 순서대로 정렬 ("1월", "2월", "3월" 등)
	    List<CountByMonthDto> monthlyResults = new ArrayList<>(monthlyResultMap.values());
	    monthlyResults.sort(Comparator.comparing(CountByMonthDto :: getMonth));

	    return monthlyResults;
	}
	
	// 사용자 유형에 따른 구독자 및 비구독자 조회
	@Override
	public SubsCountDto countSubsByUserType(UserType userType) {
		QUser user = QUser.user;

		SubsCountDto result = queryFactory
				.select(
						Projections.constructor(SubsCountDto.class,
								user.type,
								user.subscribed.when(true).then(1L).otherwise(0L).sum(),
								user.subscribed.when(false).then(1L).otherwise(0L).sum()
								)
						)
				.from(user)
				.where(user.type.eq(userType))
				.fetchOne();
		
		return result;
	}
	
	// 각 UserType에 대한 총 회원을 List로 취합하여 반환
	@Override
	public UserCountDto countUsersByUserType(UserType userType) {
		QUser user = QUser.user;

		UserCountDto result = queryFactory
				.select(
						Projections.constructor(UserCountDto.class,
								user.type,
								user.count()
								)
						)
				.from(user)
				.where(user.type.eq(userType))
				.fetchOne();
		
		return result;
	}
}
