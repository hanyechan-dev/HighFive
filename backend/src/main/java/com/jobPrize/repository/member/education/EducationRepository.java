package com.jobPrize.repository.member.education;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.member.Education;

public interface EducationRepository extends JpaRepository<Education, Long>, EducationRepositoryCostom{

}
