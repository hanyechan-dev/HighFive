package com.jobPrize.memberService.service.commom.impl;



import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.entity.common.User;
import com.jobPrize.entity.company.Company;
import com.jobPrize.entity.member.Member;
import com.jobPrize.jwt.TokenProvider;
import com.jobPrize.memberService.dto.login.LogInDto;
import com.jobPrize.memberService.dto.signUp.CompanySignUpDto;
import com.jobPrize.memberService.dto.signUp.MemberSignUpDto;
import com.jobPrize.memberService.dto.signUp.UserSignUpDto;
import com.jobPrize.memberService.service.commom.UserService;
import com.jobPrize.repository.common.UserRepository;
import com.jobPrize.repository.company.company.CompanyRepository;
import com.jobPrize.repository.member.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	
	private final MemberRepository memberRepository;
	
	private final CompanyRepository companyRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final TokenProvider tokenProvider;
	
	@Override
	public String signUpUser(UserSignUpDto userSignUpDto) {
		
		
		if(isExistEmail(userSignUpDto.getEmail())) {
			throw new IllegalStateException("이미 사용 중인 이메일입니다.");
		}
		
		String encodedPassword=passwordEncoder.encode(userSignUpDto.getPassword());
		
		User user = User.of(userSignUpDto, encodedPassword);
		userRepository.save(user);

		String accessToken = tokenProvider.createToken(user.getId(), user.getType());
		
		return accessToken;
		
	}
	

	@Override
	public void registerMemberInfo(MemberSignUpDto memberSignUpDto, String token) {
		Long id = tokenProvider.getIdFromToken(token);
		User user = userRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
		
		Member member = Member.of(user, memberSignUpDto);
		memberRepository.save(member);
		

	}

	@Override
	public void registerCompanyInfo(CompanySignUpDto companySignUpAdditionalDto, String token) {
		Long id = tokenProvider.getIdFromToken(token);
		User user = userRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
		Company company = Company.builder()
				.user(user)
				.industry(companySignUpAdditionalDto.getIndustry())
				.companyName(companySignUpAdditionalDto.getCompanyName())
				.representativeName(companySignUpAdditionalDto.getRepresentativeName())
				.businessNumber(companySignUpAdditionalDto.getBusinessNumber())
				.companyPhone(companySignUpAdditionalDto.getCompanyPhone())
				.introduction(companySignUpAdditionalDto.getIntroduction())
				.type(companySignUpAdditionalDto.getType())
				.employeeCount(companySignUpAdditionalDto.getEmployeeCount())
				.establishedDate(companySignUpAdditionalDto.getEstablishedDate())
				.build();
		
		companyRepository.save(company);
				
	}






	@Override
	public String logIn(LogInDto logInDto) {
		String email = logInDto.getEmail();
		User user = userRepository.findByEmailAndDeletedDateIsNull(email)
				.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
		
		if(!passwordEncoder.matches(logInDto.getPassword(),user.getPassword())) {
			throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
		}
		String accessToken = tokenProvider.createToken(user.getId(), user.getType());

		return accessToken;
	}
	



	
	private boolean isExistEmail(String email) {
		return userRepository.findByEmailAndDeletedDateIsNull(email).isPresent();
	}






}
