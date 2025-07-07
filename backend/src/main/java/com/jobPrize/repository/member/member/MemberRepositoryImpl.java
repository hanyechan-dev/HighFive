package com.jobPrize.repository.member.member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.jobPrize.entity.common.QUser;
import com.jobPrize.entity.member.Member;
import com.jobPrize.entity.member.QCareer;
import com.jobPrize.entity.member.QCareerDescription;
import com.jobPrize.entity.member.QCertification;
import com.jobPrize.entity.member.QCoverLetter;
import com.jobPrize.entity.member.QEducation;
import com.jobPrize.entity.member.QLanguageTest;
import com.jobPrize.entity.member.QMember;
import com.jobPrize.enumerate.EmbeddingStatus;
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
		QEducation education = QEducation.education;
		QCareer career = QCareer.career;
		QCertification certification = QCertification.certification;
		QLanguageTest languageTest = QLanguageTest.languageTest;
		QCareerDescription careerDescription = QCareerDescription.careerDescription;
		QCoverLetter coverLetter = QCoverLetter.coverLetter;
		
		List<Member> resultEdu = queryFactory
				.selectFrom(member)
				.leftJoin(member.educations, education)
				.where(member.lastUpdateTime.after(targeTime))
				.where(education.embeddingStatus.ne(EmbeddingStatus.SUCCESS))
				.distinct()
				.fetch();
		
		List<Member> resultCar = queryFactory
				.selectFrom(member)
				.leftJoin(member.careers, career)
				.where(member.lastUpdateTime.after(targeTime))
				.where(career.embeddingStatus.ne(EmbeddingStatus.SUCCESS))
				.distinct()
				.fetch();
		
		List<Member> resultCer = queryFactory
				.selectFrom(member)
				.leftJoin(member.certifications, certification)
				.where(member.lastUpdateTime.after(targeTime))
				.where(certification.embeddingStatus.ne(EmbeddingStatus.SUCCESS))
				.distinct()
				.fetch();
		
		List<Member> resultLag = queryFactory
				.selectFrom(member)
				.leftJoin(member.languageTests, languageTest)
				.where(member.lastUpdateTime.after(targeTime))
				.where(languageTest.embeddingStatus.ne(EmbeddingStatus.SUCCESS))
				.distinct()
				.fetch();
		
		List<Member> resultCdc = queryFactory
				.selectFrom(member)
				.leftJoin(member.careerDescriptions, careerDescription)
				.leftJoin(careerDescription.careerDescriptionContents)
				.where(member.lastUpdateTime.after(targeTime))
				.where(careerDescription.embeddingStatus.ne(EmbeddingStatus.SUCCESS))
				.distinct()
				.fetch();
		
		List<Member> resultClc = queryFactory
				.selectFrom(member)
				.leftJoin(member.coverLetters, coverLetter)
				.leftJoin(coverLetter.coverLetterContents)
				.where(member.lastUpdateTime.after(targeTime))
				.where(coverLetter.embeddingStatus.ne(EmbeddingStatus.SUCCESS))
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

	@Override
	public List<Member> findAll() {
		
		QMember member = QMember.member;
		QEducation education = QEducation.education;
		QCareer career = QCareer.career;
		QCertification certification = QCertification.certification;
		QLanguageTest languageTest = QLanguageTest.languageTest;
		QCareerDescription careerDescription = QCareerDescription.careerDescription;
		QCoverLetter coverLetter = QCoverLetter.coverLetter;
		
		List<Member> resultEdu = queryFactory
				.selectFrom(member)
				.leftJoin(member.educations, education)
				.where(education.embeddingStatus.eq(EmbeddingStatus.SUCCESS))
				.distinct()
				.fetch();
		
		List<Member> resultCar = queryFactory
				.selectFrom(member)
				.leftJoin(member.careers, career)
				.where(career.embeddingStatus.eq(EmbeddingStatus.SUCCESS))
				.distinct()
				.fetch();
		
		List<Member> resultCer = queryFactory
				.selectFrom(member)
				.leftJoin(member.certifications, certification)
				.where(certification.embeddingStatus.eq(EmbeddingStatus.SUCCESS))
				.distinct()
				.fetch();
		
		List<Member> resultLag = queryFactory
				.selectFrom(member)
				.leftJoin(member.languageTests, languageTest)
				.where(languageTest.embeddingStatus.eq(EmbeddingStatus.SUCCESS))
				.distinct()
				.fetch();
		
		List<Member> resultCdc = queryFactory
				.selectFrom(member)
				.leftJoin(member.careerDescriptions, careerDescription)
				.leftJoin(careerDescription.careerDescriptionContents)
				.where(careerDescription.embeddingStatus.eq(EmbeddingStatus.SUCCESS))
				.distinct()
				.fetch();
		
		List<Member> resultClc = queryFactory
				.selectFrom(member)
				.leftJoin(member.coverLetters, coverLetter)
				.leftJoin(coverLetter.coverLetterContents)
				.where(coverLetter.embeddingStatus.eq(EmbeddingStatus.SUCCESS))
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


