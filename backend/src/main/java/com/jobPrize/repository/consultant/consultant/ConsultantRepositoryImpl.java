package com.jobPrize.repository.consultant.consultant;

import java.util.Optional;

import com.jobPrize.entity.common.QUser;
import com.jobPrize.entity.consultant.Consultant;
import com.jobPrize.entity.consultant.QConsultant;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class ConsultantRepositoryImpl implements ConsultantRepositoryCustom {
	
    private final JPAQueryFactory queryFactory;

	@Override
	public Optional<Consultant> findByIdAndDeletedDateIsNull(Long id) {
		
        QConsultant consultant = QConsultant.consultant;
        QUser user = QUser.user;

        Consultant result = queryFactory
            .selectFrom(consultant)
            .join(consultant.user, user).fetchJoin()
            .where(consultant.id.eq(id),
                   user.deletedDate.isNull())
            .fetchOne();

        return Optional.ofNullable(result);
	}

}
