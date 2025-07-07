package com.jobPrize.service.memToCom.similarity;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.entity.company.JobPosting;
import com.jobPrize.entity.memToCom.Similarity;
import com.jobPrize.entity.member.Career;
import com.jobPrize.entity.member.CareerDescription;
import com.jobPrize.entity.member.Certification;
import com.jobPrize.entity.member.CoverLetter;
import com.jobPrize.entity.member.Education;
import com.jobPrize.entity.member.LanguageTest;
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

	// 신입 가중치
	private static final double educationWeight = 30;
	private static final double careerWeight = 25;
	private static final double coverLetterWeight = 15;
	private static final double careerDescriptionWeight = 12;

	// 경력 가중치
	private static final double educationForCareerWeight = 25;
	private static final double careerForCareerWeight = 30;
	private static final double coverLetterForCareerWeight = 12;
	private static final double careerDescriptionForCareerWeight = 15;

	// 공통 가중치
	private static final double certificationWeight = 10;
	private static final double languageTestWeight = 8;

	// 가중 평균을 구하기 위한 내부 클래스
	private static class WeightedVectorAggregator {
		// 가중 벡터
		double[] totalWeightedVector;
		// 총 가중치
		double totalWeight = 0.0;

		double[] baseVector = null;

		// 가중 벡터 선언하는 생성자
		WeightedVectorAggregator(int length) {
			this.totalWeightedVector = new double[length];
		}

		// 벡터와 가중치가 들어오면 가중 벡터 및 총 가중치 합산
		// 들어온 벡터가 null일 경우 아무것도 수행하지 않음
		void add(double[] vec, double weight) {
			if (vec != null) {
				if (baseVector == null)
					baseVector = vec;
				totalWeight += weight;
				for (int i = 0; i < vec.length; i++) {
					totalWeightedVector[i] += vec[i] * weight;
				}
			}
		}

		// 가중 벡터를 총 가중치로 정규화
		void normalize() {
			for (int i = 0; i < totalWeightedVector.length; i++) {
				totalWeightedVector[i] /= totalWeight;
			}
		}
	}

	@Override
	@Scheduled(cron = "0 30 * * * *")
	@Transactional
	public void calcSimilarities() {
		List<JobPosting> jobPostings = jobPostingRepository.findAllByEmbeddingStatus(EmbeddingStatus.SUCCESS);
		List<Member> members = memberRepository.findAll();

		for (JobPosting jobPosting : jobPostings) {
			for (Member member : members) {
				if (!similarityRepository.existsByMemberAndJobPosting(member, jobPosting)) {
					Map<String, double[]> representativeVectors = getRepresentativeVectors(member, jobPosting);

					int score = getSimilarityScore(representativeVectors, jobPosting.getCareerType());
					Similarity similarity = Similarity.builder().member(member).jobPosting(jobPosting).score(score)
							.build();
					similarityRepository.save(similarity);
				}

			}
		}

	}

	private Map<String, double[]> getRepresentativeVectors(Member member, JobPosting jobPosting) {

		double[] jobPostingVector = parseVector(jobPosting.getJobPostingVector());

		Map<String, double[]> vectorMap = new HashMap<String, double[]>();

		// 최종학력을 대표 벡터로 설정
		List<Education> educations = member.getEducations();
		if (educations != null && !educations.isEmpty()) {
			Education lastEducation = educations.stream().max(Comparator.comparing(Education::getEnterDate))
					.orElse(null);
			double[] educationVector = parseVector(lastEducation.getEducationVector());
			vectorMap.put("educationVector", educationVector);
		}

		// 최종경력을 대표 벡터로 설정
		List<Career> careers = member.getCareers();
		if (careers != null && !careers.isEmpty()) {
			Career lastCareer = careers.stream().max(Comparator.comparing(Career::getStartDate)).orElse(null);
			double[] careerVector = parseVector(lastCareer.getCareerVector());
			vectorMap.put("careerVector", careerVector);
		}

		// 가장 최근에 취득한 자격증에게 2배의 가중치로 설정하여 가중 평균의 결과를 대표 벡터로 설정
		List<Certification> certifications = member.getCertifications();
		if (certifications != null && !certifications.isEmpty()) {
			int certificationSize = certifications.size();
			certifications.sort(Comparator.comparing(Certification::getAcquisitionDate));
			double[] certificationVector = null;

			// 가장 최근에 취득한 자격증의 가중치 2, 나머지 1로 가중 합
			for (int i = 0; i < certificationSize; i++) {
				double[] certificationVectorItem = parseVector(certifications.get(i).getCertificationVector());

				if (i == 0) {
					certificationVector = certificationVectorItem.clone();
				} else if (i == certificationSize - 1) {
					for (int j = 0; j < certificationVectorItem.length; j++) {
						certificationVector[j] += 2 * certificationVectorItem[j];
					}
				} else {
					for (int j = 0; j < certificationVectorItem.length; j++) {
						certificationVector[j] += certificationVectorItem[j];
					}
				}

			}
			// 벡터의 가중평균 구하기
			for (int i = 0; i < certificationVector.length; i++) {
				// n게의 자격증이 있으면 (n-1)개의 가중치는 1, 1개의 가중치는 2
				// 따라서 1*(n-1) + 2*1 = n+1
				certificationVector[i] /= (double) (certificationSize + 1);
			}

			vectorMap.put("certificationVector", certificationVector);
		}

		// 어학시험 산술 평균의 결과를 대표 벡터로 설정
		List<LanguageTest> languageTests = member.getLanguageTests();

		if (languageTests != null && !languageTests.isEmpty()) {
			int languageTestSize = languageTests.size();
			double[] languageTestVector = null;

			// 산술 합
			for (int i = 0; i < languageTestSize; i++) {
				double[] languageTestVectorItem = parseVector(languageTests.get(i).getLanguageTestVector());
				if (i == 0) {
					languageTestVector = languageTestVectorItem.clone();
				} else {
					for (int j = 0; j < languageTestVectorItem.length; j++) {
						languageTestVector[j] += languageTestVectorItem[j];
					}
				}

			}

			// 벡터의 산술 평균 구하기
			for (int i = 0; i < languageTestVector.length; i++) {
				languageTestVector[i] /= (double) languageTestSize;
			}

			vectorMap.put("languageTestVector", languageTestVector);
		}
		// 채용공고와 가장 높은 Cosine Similarity를 가진 경력기술서를 대표 벡터로 설정
		List<CareerDescription> careerDescriptions = member.getCareerDescriptions();
		if (careerDescriptions != null && !careerDescriptions.isEmpty()) {
			double[] careerDescriptionVector = null;
			double cosineSimilarity = Double.MIN_VALUE;

			// 리스트를 순회하며 가장 높은 Cosine Similarity를 가진 경력기술서 찾기
			for (CareerDescription careerDescription : careerDescriptions) {
				double[] careerDescriptionVectorItem = parseVector(careerDescription.getCareerDescriptionVector());
				double cosineSimilarityItem = getCosineSimilarity(jobPostingVector, careerDescriptionVectorItem);
				if (cosineSimilarity <= cosineSimilarityItem) {
					cosineSimilarity = cosineSimilarityItem;
					careerDescriptionVector = careerDescriptionVectorItem.clone();
				}
			}
			vectorMap.put("careerDescriptionVector", careerDescriptionVector);
		}

		// 채용공고와 가장 높은 Cosine Similarity를 가진 자기소개서를 대표 벡터로 설정
		List<CoverLetter> coverLetters = member.getCoverLetters();
		if (coverLetters != null && !coverLetters.isEmpty()) {
			double[] coverLetterVector = null;
			double cosineSimilarity = Double.MIN_VALUE;

			// 리스트를 순회하며 가장 높은 Cosine Similarity를 가진 자기소개서 찾기
			for (CoverLetter coverLetter : coverLetters) {
				double[] coverLetterVectorItem = parseVector(coverLetter.getCoverLetterVector());
				double cosineSimilarityItem = getCosineSimilarity(jobPostingVector, coverLetterVectorItem);
				if (cosineSimilarity <= cosineSimilarityItem) {
					cosineSimilarity = cosineSimilarityItem;
					coverLetterVector = coverLetterVectorItem.clone();
				}
			}
			vectorMap.put("coverLetterVector", coverLetterVector);
		}

		vectorMap.put("jobPostingVector", jobPostingVector);

		return vectorMap;
	}

	private double[] parseVector(String vectorStr) {

		// 1. 앞뒤 공백 제거
		if (vectorStr == null) {
			return null;
		}

		String cleaned = vectorStr.trim();

		// 2. 따옴표 제거
		if (cleaned.startsWith("\"") && cleaned.endsWith("\"")) {
			cleaned = cleaned.substring(1, cleaned.length() - 1);
		}
		// 3. 대괄호 제거
		String trimmed = cleaned.substring(1, cleaned.length() - 1);

		// 4. 쉼표로 분리
		String[] tokens = trimmed.split(",");

		// 5. 숫자로 변환
		double[] vector = new double[tokens.length];
		for (int i = 0; i < tokens.length; i++) {
			vector[i] = Double.parseDouble(tokens[i].trim());
		}

		return vector;

	}

	private int getSimilarityScore(Map<String, double[]> representativeVectors, String careerType) {

		// Map에서 모든 값을 꺼냄
		double[] educationVector = representativeVectors.get("educationVector");
		double[] careerVector = representativeVectors.get("careerVector");
		double[] certificationVector = representativeVectors.get("certificationVector");
		double[] languageTestVector = representativeVectors.get("languageTestVector");
		double[] careerDescriptionVector = representativeVectors.get("careerDescriptionVector");
		double[] coverLetterVector = representativeVectors.get("coverLetterVector");
		double[] jobPostingVector = representativeVectors.get("jobPostingVector");

		// 길이를 계산하기 위한 배열 선언
		double[] baseVector = null;

		// 신입의 경우 학력과 자기소개서의 가중치를 높히기 위한 분기처리
		if (careerType.equals("신입")) {

			// baseVector는 null이 아닌 아무 벡터로 지정
			if (educationVector != null)
				baseVector = educationVector;
			if (careerVector != null)
				baseVector = careerVector;
			if (coverLetterVector != null)
				baseVector = coverLetterVector;
			if (careerDescriptionVector != null)
				baseVector = careerDescriptionVector;
			if (certificationVector != null)
				baseVector = certificationVector;
			if (languageTestVector != null)
				baseVector = languageTestVector;
			if (baseVector == null) {
				throw new IllegalStateException("대표 벡터가 하나도 존재하지 않습니다.");
			}

			// WeightedVectorAggregator에서 totalWeightedVector의 길이를 산정하기 위한 생성자
			WeightedVectorAggregator agg = new WeightedVectorAggregator(baseVector.length);

			// 각 인자를 넣어 가중 벡터 합산
			agg.add(educationVector, educationWeight);
			agg.add(careerVector, careerWeight);
			agg.add(coverLetterVector, coverLetterWeight);
			agg.add(careerDescriptionVector, careerDescriptionWeight);
			agg.add(certificationVector, certificationWeight);
			agg.add(languageTestVector, languageTestWeight);

			// 가중 벡터를 가중 평균 벡터로 정규화
			agg.normalize();

			double cosineSimilarity = getCosineSimilarity(agg.totalWeightedVector, jobPostingVector);
			return (int) (cosineSimilarity * 100);
		}

		// 경력의 경우 경력과 경력기술서의 가중치를 높히기 위한 분기처리
		else {
			if (educationVector != null)
				baseVector = educationVector;
			if (careerVector != null)
				baseVector = careerVector;
			if (coverLetterVector != null)
				baseVector = coverLetterVector;
			if (careerDescriptionVector != null)
				baseVector = careerDescriptionVector;
			if (certificationVector != null)
				baseVector = certificationVector;
			if (languageTestVector != null)
				baseVector = languageTestVector;
			if (baseVector == null) {
				throw new IllegalStateException("대표 벡터가 하나도 존재하지 않습니다.");
			}

			WeightedVectorAggregator agg = new WeightedVectorAggregator(baseVector.length);

			agg.add(educationVector, educationForCareerWeight);
			agg.add(careerVector, careerForCareerWeight);
			agg.add(coverLetterVector, coverLetterForCareerWeight);
			agg.add(careerDescriptionVector, careerDescriptionForCareerWeight);
			agg.add(certificationVector, certificationWeight);
			agg.add(languageTestVector, languageTestWeight);
			agg.normalize();

			double cosineSimilarity = getCosineSimilarity(agg.totalWeightedVector, jobPostingVector);
			return (int) (cosineSimilarity * 100);

		}

	}

	private double getCosineSimilarity(double[] vec1, double[] vec2) {
		// 내적
		double dotProduct = 0.0;

		// vector의 distance^2
		double normA = 0.0;
		double normB = 0.0;

		// 두개의 벡터를 순회하며 누적합을 통해 내적과 각 vector의 distance^2 구하기
		for (int i = 0; i < vec1.length; i++) {
			dotProduct += vec1[i] * vec2[i];
			normA += Math.pow(vec1[i], 2);
			normB += Math.pow(vec2[i], 2);
		}
		// 벡터에 값이 없으면 유사도 0
		if (normA == 0.0 || normB == 0.0) {
			return 0.0;
		}

		// Cosine Similarity = 내적 / 각 벡터의 distance의 곱
		return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
	}

}
