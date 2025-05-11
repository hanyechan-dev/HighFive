package com.jobPrize.repository.common;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.common.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByIdAndDeletedDateIsNull(Long id);
	Optional<User> findByEmailAndDeletedDateIsNull(String email);

}
