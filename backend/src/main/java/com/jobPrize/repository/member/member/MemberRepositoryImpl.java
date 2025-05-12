package com.jobPrize.repository.member.member;

import java.util.Optional;

import com.jobPrize.entity.member.Member;
import com.jobPrize.entity.member.QCareer;
import com.jobPrize.entity.member.QCareerDescription;
import com.jobPrize.entity.member.QCertification;
import com.jobPrize.entity.member.QCoverLetter;
import com.jobPrize.entity.member.QEducation;
import com.jobPrize.entity.member.QLanguageTest;
import com.jobPrize.entity.member.QMember;
import com.jobPrize.entity.member.QResume;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{
	
	private final JPAQueryFactory queryFactory;

	@Override
	public Optional<Member> findWithAllDocumentsByMemberId(Long id) {
		QMember member = QMember.member;
		QResume resume = QResume.resume;
		QEducation education = QEducation.education;
		QCareer career = QCareer.career;
		QCertification certification = QCertification.certification;
		QLanguageTest languageTest = QLanguageTest.languageTest;
		QCareerDescription careerDescription = QCareerDescription.careerDescription;
		QCoverLetter coverLetter = QCoverLetter.coverLetter;
		
		Member result = queryFactory
				.selectFrom(member)
				.leftJoin(member.resume, resume).fetchJoin()
				.leftJoin(resume.educations, education).fetchJoin()
				.leftJoin(resume.careers, career).fetchJoin()
				.leftJoin(resume.certifications, certification).fetchJoin()
				.leftJoin(resume.languageTests, languageTest).fetchJoin()
				.leftJoin(member.careerDescriptions, careerDescription).fetchJoin()
				.leftJoin(member.coverLetters, coverLetter).fetchJoin()
				.where(member.id.eq(id))
				.distinct()
				.fetchOne();
		
		return Optional.ofNullable(result);
	}

}
