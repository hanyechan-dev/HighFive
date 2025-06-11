package com.jobPrize.service.common.user;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.common.myPage.MyPageResponseDto;
import com.jobPrize.dto.common.myPage.MyPageUpdateDto;
import com.jobPrize.dto.common.myPage.PasswordUpdateDto;
import com.jobPrize.dto.common.token.TokenDto;
import com.jobPrize.dto.common.user.kakao.KakaoProfileDto;
import com.jobPrize.dto.common.user.kakao.KakaoUserSignUpDto;
import com.jobPrize.dto.common.user.login.LogInDto;
import com.jobPrize.dto.common.user.signUp.UserSignUpDto;
import com.jobPrize.entity.common.User;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.jwt.TokenProvider;
import com.jobPrize.repository.common.user.UserRepository;
import com.jobPrize.service.admin.admin.AdminService;
import com.jobPrize.service.consultant.consultant.ConsultantService;
import com.jobPrize.util.AssertUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;
	
	private final AdminService adminService;
	
	private final ConsultantService consultantService;

	private final TokenProvider tokenProvider;

	private final AssertUtil assertUtil;

	private static final String ENTITY_NAME = "회원";

	@Override
	public TokenDto createUser(UserSignUpDto userSignUpDto) {

		boolean isExistEmail = isExistEmail(userSignUpDto.getEmail());

		if (isExistEmail) {
			throw new IllegalStateException("이미 사용 중인 이메일입니다.");
		}

		String encodedPassword = passwordEncoder.encode(userSignUpDto.getPassword());

		User user = User.of(userSignUpDto, encodedPassword);
		

		
		if (UserType.일반회원.equals(userSignUpDto.getType())||UserType.관리자.equals(userSignUpDto.getType())) {
			user.approve();
		}
		
		userRepository.save(user);
		

		createAdminOrConsultant(user);
		

		String accessToken = tokenProvider.createAccessToken(user.getId(), user.getType(), user.getApprovalStatus(),
				user.isSubscribed());
		String refreshToken = tokenProvider.createRefreshToken(user.getId(), user.getType(), user.getApprovalStatus(),
				user.isSubscribed());

		return TokenDto.builder().accessToken(accessToken).refreshToken(refreshToken).build();

	}

	@Override
	public TokenDto logIn(LogInDto logInDto) {
		String email = logInDto.getEmail();
		User user = userRepository.findByEmailAndDeletedDateIsNull(email)
				.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));

		if (!passwordEncoder.matches(logInDto.getPassword(), user.getPassword())) {
			throw new IllegalStateException("이메일 또는 비밀번호가 일치하지 않습니다.");
		}
		String accessToken = tokenProvider.createAccessToken(user.getId(), user.getType(), user.getApprovalStatus(),
				user.isSubscribed());
		String refreshToken = tokenProvider.createRefreshToken(user.getId(), user.getType(), user.getApprovalStatus(),
				user.isSubscribed());

		return TokenDto.builder().accessToken(accessToken).refreshToken(refreshToken).build();
	}

	@Override
	@Transactional(readOnly = true)
	public MyPageResponseDto readUserMyPageInfo(Long id) {
		User user = userRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));
		return MyPageResponseDto.from(user);
	}

	@Override
	public void updateUserMyPageInfo(Long id, MyPageUpdateDto myPageUpdateDto) {
		User user = userRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));
		user.updateAddress(myPageUpdateDto.getAddress());
		user.updatePhone(myPageUpdateDto.getPhone());

	}

	@Override
	public void updateUserPassword(Long id, PasswordUpdateDto passwordUpdateDto) {
		User user = userRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));
		if (!passwordEncoder.matches(passwordUpdateDto.getPassword(), user.getPassword())) {
			throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
		}

		if (!passwordUpdateDto.getNewPassword().equals(passwordUpdateDto.getNewPasswordCheck())) {
			throw new IllegalStateException("새 비밀번호와 새 비밀번호 확인이 일치하지 않습니다.");
		}

		String encodedPassword = passwordEncoder.encode(passwordUpdateDto.getNewPassword());

		user.updatePassword(encodedPassword);

	}

	@Override
	public void softDeleteUser(Long id, UserType userType, Long targetId) {

		String action = "삭제";

		User user = userRepository.findByIdAndDeletedDateIsNull(targetId)
				.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));

		Long ownerId = user.getId();

		if (!UserType.관리자.equals(userType)) {
			assertUtil.assertId(id, ownerId, ENTITY_NAME, action);
		}

		user.deleteUser();

	}

	@Override
	public TokenDto createUserForKakao(KakaoUserSignUpDto kakaoUserSignUpDto) {
		
		String email = kakaoUserSignUpDto.getEmail();
		
		boolean isExistEmail = isExistEmail(email);
		
		if (isExistEmail) {
			throw new IllegalStateException("이미 사용 중인 이메일입니다.");
		}

		User user = User.from(kakaoUserSignUpDto);

		if (UserType.일반회원.equals(kakaoUserSignUpDto.getType())) {
			user.approve();
		}

		userRepository.save(user);
		
		createAdminOrConsultant(user);

		String accessToken = tokenProvider.createAccessToken(user.getId(), user.getType(), user.getApprovalStatus(), user.isSubscribed());
		String refreshToken = tokenProvider.createRefreshToken(user.getId(), user.getType(), user.getApprovalStatus(), user.isSubscribed());

		return TokenDto.builder().accessToken(accessToken).refreshToken(refreshToken).build();

	}

	@Override
	public TokenDto logInForKakao(String email) {

		User user = userRepository.findByEmailAndDeletedDateIsNull(email)
				.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));

		String accessToken = tokenProvider.createAccessToken(user.getId(), user.getType(), user.getApprovalStatus(),
				user.isSubscribed());
		String refreshToken = tokenProvider.createRefreshToken(user.getId(), user.getType(), user.getApprovalStatus(),
				user.isSubscribed());

		return TokenDto.builder().accessToken(accessToken).refreshToken(refreshToken).build();

	}

	@Scheduled(cron = "0 0 0 * * *")
	public void hardDeleteUsers() {
		List<User> users = userRepository.findAllForDelete();

		userRepository.deleteAll(users);
	}

	@Override
	public boolean isExistEmail(String email) {
		return userRepository.existsByEmailAndDeletedDateIsNull(email);
	}

	@Override
	public String getEmailFromKakaoAccessToken(String kakaoAccessToken) {

		WebClient webClient = WebClient.create();

		KakaoProfileDto profile = webClient.get()
		        .uri("https://kapi.kakao.com/v2/user/me")
		        .header("Authorization", "Bearer " + kakaoAccessToken)
		        .retrieve()
		        .bodyToMono(KakaoProfileDto.class)
		        .block();

		    return profile.getKakao_account().getEmail();

	}
	
	private void createAdminOrConsultant(User user) {
		Long id = user.getId();
		UserType userType = user.getType();
		
		if (UserType.컨설턴트회원.equals(userType)) {
			consultantService.createConsultant(id, userType);
		}
		else if(UserType.관리자.equals(userType)) {
			adminService.createAdmin(id, userType);
		}
	}

		
}


