package com.jobPrize.service.memToCom.similarity;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.entity.company.JobPosting;
import com.jobPrize.entity.memToCom.Similarity;
import com.jobPrize.entity.member.Member;
import com.jobPrize.enumerate.EmbeddingStatus;
import com.jobPrize.repository.company.jobPosting.JobPostingRepository;
import com.jobPrize.repository.memToCom.similarity.SimilarityRepository;
import com.jobPrize.repository.member.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SimilarityServiceImpl implements SimilarityService {
	
	private final SimilarityRepository similarityRepository;
	
	private final JobPostingRepository jobPostingRepository;
	
	private final MemberRepository memberRepository;

	@Override
	@Scheduled(cron = "0 30 * * * *")
	@Transactional
	public void calcSimilarities() {
		List<JobPosting> jobPostings = jobPostingRepository.findAllByEmbeddingStatus(EmbeddingStatus.SUCCESS);
		List<Member> members =memberRepository.findAllByEmbeddingStatus(EmbeddingStatus.SUCCESS);
		
		for(JobPosting jobPosting : jobPostings) {
			for(Member member : members) {
				if(!similarityRepository.existsByMemberAndJobPosting(member, jobPosting)) {
					int score = getSimilarityScore(member, jobPosting);
					Similarity similarity = Similarity.builder().member(member).jobPosting(jobPosting).score(score).build();
					similarityRepository.save(similarity);
				}
				
			}
		}
		
	}
	
	private int getSimilarityScore(Member member, JobPosting jobPosting) {
		double[] memberVector = parseVector(member.getMemberVector());
		double[] jobPostingVector = parseVector(jobPosting.getJobPostingVector());
		double cosineSimilarity = getCosineSimilarity(memberVector,jobPostingVector);
		return (int)(cosineSimilarity*100);
	}
	
	private double[] parseVector(String vectorStr) {
		
		String cleaned = vectorStr.trim();
		
	    if (cleaned.startsWith("\"") && cleaned.endsWith("\"")) {
	        cleaned = cleaned.substring(1, cleaned.length() - 1);
	    }
	    // 1. 대괄호 제거
	    String trimmed = cleaned.substring(1, cleaned.length() - 1);

	    // 2. 쉼표로 분리
	    String[] tokens = trimmed.split(",");

	    // 3. 숫자로 변환
	    double[] vector = new double[tokens.length];
	    for (int i = 0; i < tokens.length; i++) {
	        vector[i] = Double.parseDouble(tokens[i].trim());
	    }

	    return vector;
	}
	
	private double getCosineSimilarity(double[] vec1, double[] vec2) {
	    double dotProduct = 0.0;
	    double normA = 0.0;
	    double normB = 0.0;

	    for (int i = 0; i < vec1.length; i++) {
	        dotProduct += vec1[i] * vec2[i];
	        normA += Math.pow(vec1[i], 2);
	        normB += Math.pow(vec2[i], 2);
	    }

	    if (normA == 0.0 || normB == 0.0) {
	        return 0.0; // 벡터에 값이 없으면 유사도 0
	    }

	    return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
	}

}
