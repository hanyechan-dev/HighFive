package com.jobPrize.service.member.certification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.member.certification.CertificationCreateDto;
import com.jobPrize.dto.member.certification.CertificationResponseDto;
import com.jobPrize.dto.member.certification.CertificationUpdateDto;
import com.jobPrize.entity.member.Certification;
import com.jobPrize.entity.member.Member;
import com.jobPrize.enumerate.EmbeddingStatus;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.repository.memToCom.similarity.SimilarityRepository;
import com.jobPrize.repository.member.certification.CertificationRepository;
import com.jobPrize.repository.member.member.MemberRepository;
import com.jobPrize.util.AssertUtil;
import com.jobPrize.util.TextBuilder;
import com.jobPrize.util.WebClientUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CertificationServiceImpl implements CertificationService {

    private final CertificationRepository certificationRepository;

    private final MemberRepository memberRepository;
    
    private final SimilarityRepository similarityRepository;

	private final AssertUtil assertUtil;
	
	private final WebClientUtil webClientUtil;

	private final TextBuilder textBuilder;

	private final static String ENTITY_NAME = "자격증";

	private final static UserType ALLOWED_USER_TYPE = UserType.일반회원;


	@Override
	public CertificationResponseDto createCertification(Long id, UserType userType, CertificationCreateDto certificationCreateDto) {

		String action = "등록";
        
		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);

		Member member = memberRepository.findByIdAndDeletedDateIsNull(id)
			.orElseThrow(() -> new CustomEntityNotFoundException("회원"));

		Certification certification = Certification.of(member, certificationCreateDto);

		certificationRepository.save(certification);
		
		updateEmbedding(certification);
		
		member.changeLastUpdateTime();
		
		similarityRepository.deleteByMember(member);

		return CertificationResponseDto.from(certification);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CertificationResponseDto> readCertificationList(Long id) {

		List<Certification> certifications = certificationRepository.findAllByMemberId(id);
		List<CertificationResponseDto> certificationResponseDtos = new ArrayList<CertificationResponseDto>();

		for (Certification certification : certifications) {
			certificationResponseDtos.add(CertificationResponseDto.from(certification));
		}
		return certificationResponseDtos;
	}

	@Override
	public CertificationResponseDto updateCertification(Long id, CertificationUpdateDto certificationUpdateDto) {

		String action = "수정";

        Long certificationId = certificationUpdateDto.getId();

		Certification certification = certificationRepository.findById(certificationId)
			.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));

		Long ownerId = certificationRepository.findMemberIdByCertificationId(certificationId)
			.orElseThrow(() -> new CustomEntityNotFoundException("소유자"));

		assertUtil.assertId(id, ownerId, ENTITY_NAME, action);
        
		certification.updateCertification(certificationUpdateDto);
		
		updateEmbedding(certification);
		
		Member member = certification.getMember();
		
		member.changeLastUpdateTime();

		similarityRepository.deleteByMember(member);
		
		return CertificationResponseDto.from(certification);
	}

	@Override
	public void deleteCertification(Long id, Long certificationId) {

		String action = "삭제";
        
        Certification certification = certificationRepository.findById(certificationId)
            .orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));

		Long ownerId = certificationRepository.findMemberIdByCertificationId(certificationId)
			.orElseThrow(() -> new CustomEntityNotFoundException("소유자"));

        assertUtil.assertId(id, ownerId, ENTITY_NAME, action);
        
		Member member = certification.getMember();

        certificationRepository.delete(certification);
        
        certificationRepository.flush();
        
        similarityRepository.deleteByMember(member);
		
	}
	
	private void updateEmbedding(Certification certification) {
	    try {
	    	certification.updateEmbeddingStatus(EmbeddingStatus.PROCESSING);
	        String data = textBuilder.getCertificationStringForEmbedding(certification);
	        String vector = webClientUtil.sendEmbeddingRequestMember(data);
	        certification.updateVector(vector);
	        certification.updateEmbeddingStatus(EmbeddingStatus.SUCCESS);
	    } catch (Exception e) {
	    	certification.updateEmbeddingStatus(EmbeddingStatus.FAILED);
	    }
	}
	

}
