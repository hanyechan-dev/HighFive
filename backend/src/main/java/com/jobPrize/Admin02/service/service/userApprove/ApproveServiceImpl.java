package com.jobPrize.Admin02.service.service.userApprove;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.Admin02.service.enum_1.ApprovalStatus;
import com.jobPrize.entity.common.User;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.jwt.TokenProvider;
import com.jobPrize.repository.common.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApproveServiceImpl implements ApproveService {

	private final UserRepository userRepository;
	private final TokenProvider tokenProvider;

	@Override
	@Transactional
	public void singUpApproveUser(Long userId, String token) {
		UserType userType = tokenProvider.getUserTypeFromToken(token);
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("가입 유저가 존재하지 않습니다"));

		if (userType != UserType.관리자) {
			throw new IllegalArgumentException("관리자만 승인 할 수 있습니다");
		}

		if (user.getType() == UserType.일반회원) {
			user.approve();
			return;
		}

		if (user.getType() == UserType.기업회원 || user.getType() == UserType.컨설턴트회원) {
			user.approve();
		} else {
			throw new IllegalArgumentException("승인 할 수 없는 회원입니다");
		}
	}

	@Override
	@Transactional
	public void singUpRejectUser(Long userId, String token) {
		UserType userType = tokenProvider.getUserTypeFromToken(token);
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("가입 유저가 존재하지 않습니다"));

		if (userType != UserType.관리자) {
			throw new IllegalArgumentException("관리자만 거절 할 수 있습니다");
		}
		if (user.getType() == UserType.일반회원) {
			throw new IllegalArgumentException("일반회원은 거절 할 수 없습니다");
		}
		user.reject();
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> waitingUsers() {
		List<User> allUsers = userRepository.findAll();
		List<User> waitingUsers = new ArrayList<>();

		for (User user : allUsers) {
			if (user.getApprovalStatus() == ApprovalStatus.WAITING) {
				waitingUsers.add(user);
			}
		}

		return waitingUsers;
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> approvedUsersCheck() {
		List<User> allUsers = userRepository.findAll();
		List<User> approvedUsers = new ArrayList<>();

		for (User user : allUsers) {
			if (user.getApprovalStatus() == ApprovalStatus.APPROVED) {
				approvedUsers.add(user);
			}
		}
		return approvedUsers;
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> rejectedUsersCheck() {
		List<User> allUsers = userRepository.findAll();
		List<User> rejectUsers = new ArrayList<>();

		for (User user : allUsers) {
			if (user.getApprovalStatus() == ApprovalStatus.REJECTED) {
				rejectUsers.add(user);
			}
		}
		return rejectUsers;
	}
}
