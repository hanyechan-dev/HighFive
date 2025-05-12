package com.jobPrize.repository.member.careerDescription;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.member.CareerDescription;

public interface CareerDescriptionRepository extends JpaRepository<CareerDescription, Long>,CareerDescriptionRepositoryCustom {

}
