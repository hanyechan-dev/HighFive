package com.jobPrize.repository.member.member;

import java.util.Optional;

import com.jobPrize.entity.common.QUser;
import com.jobPrize.entity.member.Member;
import com.jobPrize.entity.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Member> findByIdAndDeletedDateIsNull(Long id) {
        QMember member = QMember.member;
        QUser user = QUser.user;

        Member result = queryFactory
            .selectFrom(member)
            .join(member.user, user).fetchJoin()
            .where(member.id.eq(id),
                   user.deletedDate.isNull())
            .fetchOne();

        return Optional.ofNullable(result);
    }

}


