package com.jobPrize.memberService.service.commom;



import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.entity.common.User;
import com.jobPrize.jwt.TokenProvider;
import com.jobPrize.memberService.dto.login.LogInDto;
import com.jobPrize.memberService.dto.myPage.MyPageResponseDto;
import com.jobPrize.memberService.dto.myPage.MyPageUpdateDto;
import com.jobPrize.memberService.dto.myPage.PasswordUpdateDto;
import com.jobPrize.memberService.dto.signUp.UserSignUpDto;
import com.jobPrize.memberService.dto.token.TokenDto;
import com.jobPrize.memberService.service.commom.UserService;
import com.jobPrize.repository.common.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final TokenProvider tokenProvider;
	
	@Override
	public TokenDto signUpUser(UserSignUpDto userSignUpDto) {
		
		
		if(isExistEmail(userSignUpDto.getEmail())) {
			throw new IllegalStateException("이미 사용 중인 이메일입니다.");
		}
		
		String encodedPassword=passwordEncoder.encode(userSignUpDto.getPassword());
		
		User user = User.of(userSignUpDto, encodedPassword);
		userRepository.save(user);

		String accessToken = tokenProvider.createAccessToken(user.getId(), user.getType());
		String refreshToken = tokenProvider.createRefreshToken(user.getId(), user.getType());
		
		return TokenDto.builder().accessToken(accessToken).refreshToken(refreshToken).build();	
		
	}
	



//	@Override
//	public void registerCompanyInfo(CompanySignUpDto companySignUpAdditionalDto, String token) {
//		Long id = tokenProvider.getIdFromToken(token);
//		User user = userRepository.findByIdAndDeletedDateIsNull(id)
//				.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
//		Company company = Company.builder()
//				.user(user)
//				.industry(companySignUpAdditionalDto.getIndustry())
//				.companyName(companySignUpAdditionalDto.getCompanyName())
//				.representativeName(companySignUpAdditionalDto.getRepresentativeName())
//				.businessNumber(companySignUpAdditionalDto.getBusinessNumber())
//				.companyPhone(companySignUpAdditionalDto.getCompanyPhone())
//				.introduction(companySignUpAdditionalDto.getIntroduction())
//				.type(companySignUpAdditionalDto.getType())
//				.employeeCount(companySignUpAdditionalDto.getEmployeeCount())
//				.establishedDate(companySignUpAdditionalDto.getEstablishedDate())
//				.build();
//		
//		companyRepository.save(company);
//				
//	}






	@Override
	public TokenDto logIn(LogInDto logInDto) {
		String email = logInDto.getEmail();
		User user = userRepository.findByEmailAndDeletedDateIsNull(email)
				.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
		
		if(!passwordEncoder.matches(logInDto.getPassword(),user.getPassword())) {
			throw new IllegalStateException("이메일 또는 비밀번호가 일치하지 않습니다.");
		}
		String accessToken = tokenProvider.createAccessToken(user.getId(), user.getType());
		String refreshToken = tokenProvider.createRefreshToken(user.getId(), user.getType());
		
		return TokenDto.builder().accessToken(accessToken).refreshToken(refreshToken).build();	
	}
	
	
	
	@Override
	public MyPageResponseDto getUserMyPageInfo(String token) {
		Long id = tokenProvider.getIdFromToken(token);
		User user = userRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
		return MyPageResponseDto.from(user);
	}


	@Override
	public void updateUserMyPageInfo(String token, MyPageUpdateDto myPageUpdateDto) {
		Long id = tokenProvider.getIdFromToken(token);
		User user = userRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
		user.updateAddress(myPageUpdateDto.getAddress());
		user.updatePhone(myPageUpdateDto.getPhone());
		
	}


	@Override
	public void updateUserPassword(String token, PasswordUpdateDto passwordUpdateDto) {
		Long id = tokenProvider.getIdFromToken(token);
		User user = userRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
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
	public void softDeleteUser(String token) {
		Long id = tokenProvider.getIdFromToken(token);
		User user = userRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
		user.deleteUser();
		
		
	}



	
	private boolean isExistEmail(String email) {
		return userRepository.findByEmailAndDeletedDateIsNull(email).isPresent();
	}









}
