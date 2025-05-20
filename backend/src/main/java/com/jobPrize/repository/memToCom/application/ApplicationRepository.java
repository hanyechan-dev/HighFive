package com.jobPrize.repository.memToCom.application;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.memToCom.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long>,ApplicationRepositoryCustom{

}
