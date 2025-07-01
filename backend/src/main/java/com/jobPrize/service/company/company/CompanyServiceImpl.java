package com.jobPrize.service.company.company;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.customException.CustomIllegalArgumentException;
import com.jobPrize.dto.company.company.CompanyCreateDto;
import com.jobPrize.dto.company.company.CompanyResponseDto;
import com.jobPrize.dto.company.company.CompanyUpdateDto;
import com.jobPrize.entity.common.User;
import com.jobPrize.entity.company.Company;
import com.jobPrize.enumerate.ImageType;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.repository.common.user.UserRepository;
import com.jobPrize.repository.company.company.CompanyRepository;
import com.jobPrize.util.AssertUtil;

import com.jobPrize.util.FileUtil;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

	private final UserRepository userRepository;

	private final CompanyRepository companyRepository;

	private final AssertUtil assertUtil;

	private static final String ENTITY_NAME = "기업";

	private final FileUtil fileUtil;

	@Value("${file.upload-company-logo-dir}")
	private String uploadDir;

	@Override
	public void createCompanyInfo(Long id, UserType usertype, CompanyCreateDto companyCreateDto,
			MultipartFile logoImageFile) {

		String action = "등록";

		assertUtil.assertUserType(usertype, UserType.기업회원, ENTITY_NAME, action);

		User user = userRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new CustomEntityNotFoundException("유저"));

		String uuidName = null;
		if (logoImageFile != null && !logoImageFile.isEmpty()) {
			uuidName = fileUtil.saveImageAndGetUUIDName(logoImageFile, ImageType.LOGO_IMAGE);
			if (uuidName == null) {
				throw new CustomIllegalArgumentException("회사 로고 이미지 저장에 실패했습니다.");
			}
		}

		Company company = Company.of(user, companyCreateDto, uuidName);

		companyRepository.save(company);

	}

	@Override
	@Transactional(readOnly = true)
	public CompanyResponseDto readCompanyInfo(Long id) {

		Company company = companyRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new CustomEntityNotFoundException("기업"));
		
		String companyImageUrl = "/images/jobposting/" + company.getLogoImageName();
		return CompanyResponseDto.of(company, companyImageUrl);
	}

	@Override
	public void updateCompanyInfo(Long id, CompanyUpdateDto companyUpdateDto, MultipartFile logoImageFile) {

		Company company = companyRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new CustomEntityNotFoundException("기업"));

		String uuidName = company.getLogoImageName();
		if (logoImageFile != null && !logoImageFile.isEmpty()) {
			if (uuidName != null) {
				deleteCompanyLogoFile(uuidName);
			}
			uuidName = fileUtil.saveImageAndGetUUIDName(logoImageFile, ImageType.LOGO_IMAGE);
			if (uuidName == null) {
				throw new CustomIllegalArgumentException("회사 로고 이미지 저장에 실패했습니다.");
			}
		}
		company.updateCompanyInfo(companyUpdateDto, uuidName);

	}

	private void deleteCompanyLogoFile(String uuidName) {
		if (uuidName != null && !uuidName.isEmpty()) {
			File file = new File(uploadDir + uuidName);
			if (file.exists()) {
				boolean deleted = file.delete();
				if (!deleted) {
					System.err.println("파일 삭제 실패: " + file.getAbsolutePath());
				}
			} else {
				System.out.println("삭제하려는 로고 파일이 존재하지 않습니다: " + file.getAbsolutePath());
			}
		}
	}

}
