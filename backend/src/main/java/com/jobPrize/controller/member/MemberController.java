package com.jobPrize.controller.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.myPage.MyPageResponseDto;
import com.jobPrize.dto.member.member.MemberCreateDto;
import com.jobPrize.dto.member.member.MemberMyPageResponseDto;
import com.jobPrize.dto.member.member.MemberResponseDto;
import com.jobPrize.dto.member.member.MemberUpdateDto;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.service.common.user.UserService;
import com.jobPrize.service.member.member.MemberService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
	
	private final UserService userService;
	
	private final MemberService memberService;
	
	
	@GetMapping
	public ResponseEntity<MemberMyPageResponseDto> readMyPage(){
		
		Long id = SecurityUtil.getId();
		
		MyPageResponseDto myPageResponseDto = userService.readUserMyPageInfo(id);
		
		MemberResponseDto memberResponseDto = memberService.readMemberInfo(id);
		
		MemberMyPageResponseDto memberMyPageResponseDto = MemberMyPageResponseDto.of(memberResponseDto, myPageResponseDto);
		
		return ResponseEntity.status(HttpStatus.OK).body(memberMyPageResponseDto);
	}
	
	
	
	@PostMapping
	public ResponseEntity<Void> createMember(@RequestBody @Valid MemberCreateDto memberCreateDto) {
		
		Long id = SecurityUtil.getId();
		
		UserType userType = SecurityUtil.getUserType();
		
		memberService.createMemberInfo(id, userType, memberCreateDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
		
	}
	

	

	
	@PutMapping
	public ResponseEntity<MemberResponseDto> updateMyNickName(@RequestBody @Valid MemberUpdateDto memberUpdateDto){
		
		Long id = SecurityUtil.getId();
		
		MemberResponseDto memberResponseDto = memberService.updateMemberInfo(id, memberUpdateDto);
		
		
		return ResponseEntity.status(HttpStatus.OK).body(memberResponseDto);
	}

	
	
	


	


	

	
	
	
	
	
	
	

	

}
