package com.jobPrize.controller.company;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.myPage.MyPageResponseDto;
import com.jobPrize.dto.company.company.CompanyCreateDto;
import com.jobPrize.dto.company.company.CompanyResponseDto;
import com.jobPrize.dto.company.company.CompanyUpdateDto;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.service.common.user.UserService;
import com.jobPrize.service.company.company.CompanyService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

	
	private final UserService userService;
	
	private final CompanyService companyService;
	
	
	@GetMapping
	public ResponseEntity<Map<String,Object>> readMyPage(){
		
		Long id = SecurityUtil.getId();
		
		MyPageResponseDto myPageResponseDto = userService.readUserMyPageInfo(id);
		
		CompanyResponseDto companyResponseDto = companyService.readCompanyInfo(id);
		
		Map<String,Object> map = new HashMap<>();
		
		map.put("myPageResponseDto",myPageResponseDto);
		map.put("companyResponseDto",companyResponseDto);
		
		return ResponseEntity.status(HttpStatus.OK).body(map);
	}
	
	
	
	@PostMapping
	public ResponseEntity<Void> createCompany(@RequestBody @Valid CompanyCreateDto companyCreateDto) {
		
		Long id = SecurityUtil.getId();
		
		UserType userType = SecurityUtil.getUserType();
		
		companyService.createCompanyInfo(id, userType, companyCreateDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
		
	}
	

	

	
	@PutMapping
	public ResponseEntity<Void> updateMyCompany(@RequestBody @Valid CompanyUpdateDto companyUpdateDto){
		
		Long id = SecurityUtil.getId();
		
		companyService.updateCompanyInfo(id, companyUpdateDto);
		
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
