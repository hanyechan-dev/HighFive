package com.jobPrize.service.common.user;



import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.dto.common.myPage.MyPageResponseDto;
import com.jobPrize.dto.common.myPage.MyPageUpdateDto;
import com.jobPrize.dto.common.myPage.PasswordUpdateDto;
import com.jobPrize.dto.common.token.TokenDto;
import com.jobPrize.dto.common.user.login.LogInDto;
import com.jobPrize.dto.common.user.signUp.UserSignUpDto;
import com.jobPrize.entity.common.User;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.jwt.TokenProvider;
import com.jobPrize.repository.common.user.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final TokenProvider tokenProvider;
	
	@Override
	public TokenDto createUser(UserSignUpDto userSignUpDto) {
		
		
		if(isExistEmail(userSignUpDto.getEmail())) {
			throw new IllegalStateException("이미 사용 중인 이메일입니다.");
		}
		
		String encodedPassword=passwordEncoder.encode(userSignUpDto.getPassword());
		
		User user = User.of(userSignUpDto, encodedPassword);
		
		if(userSignUpDto.getType()==UserType.일반회원) {
			user.approve();
		}
		
		userRepository.save(user);

		String accessToken = tokenProvider.createAccessToken(user.getId(), user.getType());
		String refreshToken = tokenProvider.createRefreshToken(user.getId(), user.getType());
		
		return TokenDto.builder().accessToken(accessToken).refreshToken(refreshToken).build();	
		
	}
	

	@Override
	public TokenDto logIn(LogInDto logInDto) {
		String email = logInDto.getEmail();
		User user = userRepository.findByEmailAndDeletedDateIsNull(email)
				.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다."));
		
		if(!passwordEncoder.matches(logInDto.getPassword(),user.getPassword())) {
			throw new IllegalStateException("이메일 또는 비밀번호가 일치하지 않습니다.");
		}
		String accessToken = tokenProvider.createAccessToken(user.getId(), user.getType());
		String refreshToken = tokenProvider.createRefreshToken(user.getId(), user.getType());
		
		return TokenDto.builder().accessToken(accessToken).refreshToken(refreshToken).build();	
	}
	
	
	
	@Override
	@Transactional(readOnly = true)
	public MyPageResponseDto readUserMyPageInfo(Long id) {
		User user = userRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다."));
		return MyPageResponseDto.from(user);
	}


	@Override
	public void updateUserMyPageInfo(Long id, MyPageUpdateDto myPageUpdateDto) {
		User user = userRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다."));
		user.updateAddress(myPageUpdateDto.getAddress());
		user.updatePhone(myPageUpdateDto.getPhone());
		
	}


	@Override
	public void updateUserPassword(Long id, PasswordUpdateDto passwordUpdateDto) {
		User user = userRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다."));
		if(!passwordEncoder.matches(passwordUpdateDto.getPassword(),user.getPassword())) {
			throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
		}
		
		if(!passwordUpdateDto.getNewPassword().equals(passwordUpdateDto.getNewPasswordCheck())){
			throw new IllegalStateException("새 비밀번호와 새 비밀번호 확인이 일치하지 않습니다.");
		}
		
		String encodedPassword=passwordEncoder.encode(passwordUpdateDto.getNewPassword());
		
		user.updatePassword(encodedPassword);
		
	}


	@Override
	public void softDeleteUser(Long id) {
		User user = userRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다."));
		user.deleteUser();
		
		
	}

	
	private boolean isExistEmail(String email) {
		return userRepository.findByEmailAndDeletedDateIsNull(email).isPresent();
	}









}
