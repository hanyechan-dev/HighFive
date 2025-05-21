package com.jobPrize.repository.common.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.common.User;
import com.jobPrize.entity.common.UserType;

public interface UserRepositoryCustom {
	Page<User> findAllWithSubEntityByUserType(UserType userType, Pageable pageable);
	Page<User> findAllWithSubEntityByUserTypeAndApprovalStatusIsWaiting(UserType userType, Pageable pageable);
}
