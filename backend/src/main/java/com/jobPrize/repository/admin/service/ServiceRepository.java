package com.jobPrize.repository.admin.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.common.User;

public interface ServiceRepository extends JpaRepository<User, Long>, ServiceRepositoryCustom {

}
