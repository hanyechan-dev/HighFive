package com.jobPrize.repository.member.application;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.memToCom.Application;

public interface MemberApplicationRepository extends JpaRepository<Application, Long>{

}
