package com.jobPrize.repository.common.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.common.User;

public interface ServiceRepository extends JpaRepository<User, Long>, ServiceRepositoryCustom {

}
