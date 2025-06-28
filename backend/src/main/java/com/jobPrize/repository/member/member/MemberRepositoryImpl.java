package com.jobPrize.repository.member.member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.jobPrize.entity.common.QUser;
import com.jobPrize.entity.member.Member;
import com.jobPrize.entity.member.QCareerDescription;
import com.jobPrize.entity.member.QCoverLetter;
import com.jobPrize.entity.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.transaction.annotation.Transactional;
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
            .join(member.user).fetchJoin()
            .where(member.id.eq(id),
                   user.deletedDate.isNull())
            .fetchOne();

        return Optional.ofNullable(result);
    }

	@Override
	@Transactional
	public List<Member> findAllByUpdateTimeWithinOneHour() {
		LocalDateTime targeTime = LocalDateTime.now().minusHours(1);
		
		QMember member = QMember.member;
		QCareerDescription careerDescription = QCareerDescription.careerDescription;
		QCoverLetter coverLetter = QCoverLetter.coverLetter;
		
		List<Member> resultEdu = queryFactory
				.selectFrom(member)
				.leftJoin(member.educations)
				.where(member.updateTime.after(targeTime))
				.distinct()
				.fetch();
		
		List<Member> resultCar = queryFactory
				.selectFrom(member)
				.leftJoin(member.careers)
				.where(member.updateTime.after(targeTime))
				.distinct()
				.fetch();
		
		List<Member> resultCer = queryFactory
				.selectFrom(member)
				.leftJoin(member.certifications)
				.where(member.updateTime.after(targeTime))
				.distinct()
				.fetch();
		
		List<Member> resultLag = queryFactory
				.selectFrom(member)
				.leftJoin(member.languageTests)
				.where(member.updateTime.after(targeTime))
				.distinct()
				.fetch();
		
		List<Member> resultCdc = queryFactory
				.selectFrom(member)
				.leftJoin(member.careerDescriptions, careerDescription)
				.leftJoin(careerDescription.careerDescriptionContents)
				.where(member.updateTime.after(targeTime))
				.distinct()
				.fetch();
		
		List<Member> resultClc = queryFactory
				.selectFrom(member)
				.leftJoin(member.coverLetters, coverLetter)
				.leftJoin(coverLetter.coverLetterContents)
				.where(member.updateTime.after(targeTime))
				.distinct()
				.fetch();
		
		Set<Member> merged = new LinkedHashSet<>();
		
		merged.addAll(resultEdu);
		merged.addAll(resultCar);
		merged.addAll(resultCer);
		merged.addAll(resultLag);
		merged.addAll(resultCdc);
		merged.addAll(resultClc);
		
		List<Member> results = new ArrayList<>(merged);
		
		return results;
	}

}


