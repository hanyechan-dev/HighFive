package com.jobPrize.repository.member.career;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.member.Career;

public interface CareerRepository extends JpaRepository<Career, Long>, CareerRepositoryCustom {

}
