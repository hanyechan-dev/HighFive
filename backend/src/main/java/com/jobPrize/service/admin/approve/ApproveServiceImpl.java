package com.jobPrize.service.admin.approve;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.entity.common.User;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.repository.common.user.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ApproveServiceImpl implements ApproveService {

	private final UserRepository userRepository;

	@Override
	public void approveUser(UserType userType, Long targetUserId) {
		
		User user = userRepository.findById(targetUserId)
				.orElseThrow(() -> new EntityNotFoundException("가입 유저가 존재하지 않습니다"));

		if (userType != UserType.관리자) {
			throw new AccessDeniedException("관리자만 승인 할 수 있습니다");
		}
		
		if (user.getType() == UserType.일반회원 || user.getType() == UserType.관리자) {
			throw new IllegalArgumentException("승인 할 수 없는 회원입니다");
			
		}
		
		user.approve();
		userRepository.save(user);

	}
	
	
	@Override
	public void rejectUser(UserType userType, Long targetUserId) {
		User user = userRepository.findById(targetUserId)
				.orElseThrow(() -> new EntityNotFoundException("가입 유저가 존재하지 않습니다"));

		if (userType != UserType.관리자) {
			throw new AccessDeniedException("관리자만 거절 할 수 있습니다");
		}
		if (user.getType() == UserType.일반회원 || user.getType() == UserType.관리자 ) {
			throw new IllegalArgumentException("일반회원은 거절 할 수 없습니다");
		}
		
		user.reject();
		userRepository.save(user);
		
	}

}
