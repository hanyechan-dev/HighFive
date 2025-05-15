//package com.jobPrize.repository.common;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//
//import com.jobPrize.config.QuerydslConfig;
//import com.jobPrize.entity.company.JobPosting;
//import com.jobPrize.entity.company.JobPostingImage;
//import com.jobPrize.repository.company.Industry.IndustryRepository;
//import com.jobPrize.repository.company.advertisement.AdvertisementRepository;
//import com.jobPrize.repository.company.company.CompanyRepository;
//import com.jobPrize.repository.company.jobPosting.CompanyJobPostingRepository;
//import com.jobPrize.repository.company.jobPostingImage.CompanyJobPostingImageRepository;
//import com.jobPrize.repository.company.schedule.ScheduleRepository;
//import com.jobPrize.repository.member.member.MemberRepository;
//
//import jakarta.persistence.EntityManager;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Import(QuerydslConfig.class)
//public class CompanyRepositoryTest {
//   
//   @Autowired
//   private UserRepository userRepository;
//   
//   @Autowired
//   private MemberRepository memberRepository;
//   
//   @Autowired
//   private IndustryRepository industryRepository;
//   
//   @Autowired
//   private CompanyRepository companyRepository;
//
//	
//   @Autowired
//	private ScheduleRepository scheduleRepository;
//
//	@Autowired
//	private AdvertisementRepository advertisementRepository;
//
//	@Autowired
//	private CompanyJobPostingImageRepository jobPostingImageRepository;
//
//	@Autowired
//	private CompanyJobPostingRepository companyJobPostingRepository;
//
//	@Autowired
//	private EntityManager em;

	/*
	 * @Test
	 * 
	 * @Rollback(false)
	 * 
	 * @DisplayName("Industry 저장") void saveIndustries() { String[] industryNames =
	 * { "IT", "금융", "제조", "물류", "의료","에너지","건설","엔터테인먼트","교육","농업" }; String[]
	 * descriptions = { "정보기술 및 소프트웨어 산업", "은행, 증권, 보험 등 금융 관련 산업",
	 * "기계, 전자 등 제조업 기반 산업", "운송, 창고관리 등 물류 산업", "병원, 제약 등 의료 산업",
	 * "친환경 기술, 석유,가스 및 탄소중립 관련 산업", "주택 개발, 인프라 건설, 부동산 투자 및 관리 관련 산업",
	 * "영화, 음악, 게임, 방송, 광고 및 디지털 콘텐츠 제작 산업", "학교, 온라인 교육, 학습 플랫폼 및 AI 기반 교육 솔루션 산업",
	 * "스마트 농업, 식품 제조, 유통, 식당 및 바이오 농업 관련 산업" };
	 * 
	 * for (int i = 0; i < industryNames.length; i++) { Industry industries =
	 * Industry .builder() .industryName(industryNames[i])
	 * .description(descriptions[i]) .build();
	 * 
	 * industryRepository.save(industry);
	 * 
	 * }
	 * 
	 * em.flush(); em.clear(); }
	 * 
	 * 
	 * @Test
	 * 
	 * @Rollback(false)
	 * 
	 * @DisplayName("User 저장") void saveUser() { String[] names = { "김철수", "이영희",
	 * "박민수", "최지훈", "정수빈", "한서준", "유하늘", "장민정", "오세훈", "배지은" }; String[] emails = {
	 * "chulsoo.kim@gmail.com", "younghee.lee@naver.com", "minsoo.park@daum.net",
	 * "jihoon.choi@kakao.com", "soobin.jung@nate.com", "seojun.han@outlook.com",
	 * "haneul.yoo@icloud.com", "minjung.jang@yahoo.co.kr", "sehoon.oh@toss.im",
	 * "jieun.bae@zmail.com" }; String[] addresses = { "서울 강남구", "서울 서초구", "서울 송파구",
	 * "서울 마포구", "서울 종로구", "부산 해운대구", "대구 수성구", "인천 연수구", "광주 북구", "대전 서구" };
	 * 
	 * for (int i = 0; i < 10; i++) { User user = User .builder() .email(emails[i])
	 * .password("pass1234") .name(names[i]) .phone("0101234" + String
	 * .format("%04d", i)) .address(addresses[i]) .type(UserType.일반회원) .build();
	 * 
	 * userRepository.save(user); }
	 * 
	 * em.flush(); em.clear(); }
	 * 
	 * 
	 * @Test
	 * 
	 * @Rollback(false)
	 * 
	 * @DisplayName("User 및 Company 저장") void saveCompanies() { List<Industry>
	 * industries = industryRepository.findAll(); // 업종 미리 조회
	 * 
	 * String[] companyNames = { "네이버", "카카오", "배달의민족", "토스", "무신사", "네오테크",
	 * "스타트웍스", "스마트솔루션", "미래테크","글로벌아이디어"}; String[] representativeNames = { "최수연",
	 * "홍은택", "김봉진", "이승건", "조만호", "김철수", "이영희", "박민수", "최지훈", "정수빈"}; String[]
	 * businessNumbers = { "123-456-7890", "234-567-8901", "345-678-9012",
	 * "456-789-0123", "5678901234", "123-45-67890", "234-56-78901", "345-67-89012",
	 * "456-78-90123", "567-89-01234" }; String[] addresses = { "경기 성남시", "제주 제주시",
	 * "서울 송파구", "서울 강남구", "서울 성동구", "부산 해운대구", "인천 남동구", "광주 북구", "대전 서구", "울산 중구"
	 * }; String[] phones = { "0311234567", "0649876543", "0255557777",
	 * "0277778888", "0266669999", "010-1111-2222", "010-2222-3333",
	 * "010-3333-4444", "010-5555-6666", "010-7777-8888" };
	 * 
	 * for (int i = 0; i < 10; i++) {
	 * 
	 * User user = User.builder().email("company" + i +
	 * "@job.com").password("pass1234")
	 * .name(representativeNames[i]).phone("0108888" + String.format("%04d",
	 * i)).address(addresses[i]) .type(UserType.기업회원).build();
	 * 
	 * userRepository.save(user);
	 * 
	 * 
	 * Company company = Company.builder() .industry(industries.get(i %
	 * industries.size())) // 업종 하나씩 연결 .companyName(companyNames[i])
	 * .representativeName(representativeNames[i])
	 * .businessNumber(businessNumbers[i]) .companyAddress(addresses[i])
	 * .companyPhone(phones[i]) .introduction(companyNames[i] +
	 * "는 대한민국 대표 IT 기업입니다.") .type(i % 3 == 0 ? CompanyType.대기업 : i % 3 == 1
	 * ?CompanyType.중견기업 : CompanyType.중소기업) .employeeCount(i % 3 == 0 ? 500 + i *
	 * 100 : i % 3 == 1 ? 200 + i * 50 : 50 + i * 20)
	 * .establishedDate(LocalDate.of(2000 + i, 1, 1)) .build();
	 * 
	 * companyRepository.save(company); }
	 * 
	 * em.flush(); em.clear(); }
	 * 
	 * @Test
	 * 
	 * @Rollback(false)
	 * 
	 * @DisplayName("Schedule 저장") void saveSchedules() { List<Company> companies=
	 * companyRepository.findAll();
	 * 
	 * String[] titles = { "개발자 컨퍼런스", "AI 세미나", "클라우드 기술 워크숍", "웹 개발 해커톤",
	 * "취업 박람회", "기업 네트워킹 행사", "기술 트렌드 분석", "데이터 사이언스 강연", "블록체인 포럼",
	 * "오픈 소스 프로젝트 설명회" };
	 * 
	 * String[] contents = { "최신 개발 트렌드를 논의하는 자리입니다.", "AI 전문가들이 모여 기술을 논의합니다.",
	 * "클라우드 서비스 활용법을 배우는 워크숍입니다.", "웹 개발자들을 위한 해커톤 대회!",
	 * "다양한 기업의 채용 정보를 제공하는 박람회.", "기업 간 협업을 위한 네트워킹 행사입니다.",
	 * "최신 기술 트렌드에 대한 깊이 있는 분석을 공유합니다.", "데이터 과학과 AI의 융합에 대해 강연합니다.",
	 * "블록체인 기술의 미래와 활용 방안을 논의합니다.", "오픈 소스 개발 프로젝트를 소개하는 자리입니다." };
	 * 
	 * LocalDate[] dates = { LocalDate.of(2025, 5, 25), LocalDate.of(2025, 6, 10),
	 * LocalDate.of(2025, 6, 15), LocalDate.of(2025, 6, 22), LocalDate.of(2025, 7,
	 * 5), LocalDate.of(2025, 7, 12), LocalDate.of(2025, 7, 20), LocalDate.of(2025,
	 * 8, 3), LocalDate.of(2025, 8, 15), LocalDate.of(2025, 8, 25) };
	 * 
	 * for (int i = 0; i < titles.length; i++) { Schedule schedule =
	 * Schedule.builder() .company(companies.get(i % companies.size()))
	 * .title(titles[i]) .content(contents[i]) .date(dates[i]) .build();
	 * 
	 * scheduleRepository.save(schedule); } }
	 */

//	@Test
//	@Rollback(false)
//	@DisplayName("Advertisement 저장")
//	void saveAdvertisement() { 
//	
//		List<Company> companies= companyRepository.findAll();
//		
//		if (companies.isEmpty()) {
//	        throw new IllegalStateException("저장된 Company 데이터가 없습니다! 먼저 Company 데이터를 추가하세요.");
//	    } //기억해놓을라고 걸어놈
//
//	
//	String[] imageUrls = {"https://art.mbcs.kr/네이버-블로그-사진-이미지-링크-거는-법-전화연결-url/",
//		    "https://the7eagles.com/what-is-an-image-url/",
//		    "https://www.youtube.com/watch?v=sos8Xk4haPk",
//		    "https://nl.aiseesoft.com/tutorial/jpg-to-url.html",
//		    "https://designbase.co.kr/midjourney-01-5/",
//		    "https://m.blog.naver.com/nudlelove/220592937835",
//		    "https://infohelpful.com/%ec%9d%b4%eb%af%b8%ec%a7%80-%ed%8c%8c%ec%9d%bc-url-%eb%a7%8c%eb%93%a4%ea%b8%b0/",
//		    "https://m.blog.naver.com/xxaloneflyxx/221610942725",
//		    "https://designbase.co.kr/midjourney-01-5/",
//		    "https://m.blog.naver.com/nudlelove/220592937835"
//		};
//	LocalDate[] startDates = {
//		    LocalDate.of(2025, 5, 25),
//		    LocalDate.of(2025, 6, 4),
//		    LocalDate.of(2025, 6, 14),
//		    LocalDate.of(2025, 6, 24),
//		    LocalDate.of(2025, 7, 4),
//		    LocalDate.of(2025, 7, 14),
//		    LocalDate.of(2025, 7, 24),
//		    LocalDate.of(2025, 8, 3),
//		    LocalDate.of(2025, 8, 13),
//		    LocalDate.of(2025, 8, 23)
//		};
//
//	LocalDate[] endDates = {
//		    LocalDate.of(2025, 5, 30),
//		    LocalDate.of(2025, 6, 9),
//		    LocalDate.of(2025, 6, 19),
//		    LocalDate.of(2025, 6, 29),
//		    LocalDate.of(2025, 7, 9),
//		    LocalDate.of(2025, 7, 19),
//		    LocalDate.of(2025, 7, 29),
//		    LocalDate.of(2025, 8, 8),
//		    LocalDate.of(2025, 8, 18),
//		    LocalDate.of(2025, 8, 28)
//		};
//	
//	 if (imageUrls.length != startDates.length || imageUrls.length != endDates.length) {
//	        throw new IllegalStateException("imageUrls, startDates, endDates의 개수가 다릅니다! 배열 크기를 맞춰주세요.");
//	    }
//	
//	 List<Advertisement> advertisements = new ArrayList<>();
//
//	    for (int i = 0; i < imageUrls.length; i++) {
//	        Advertisement advertisement = Advertisement.builder()
//	                .company(companies.get(i % companies.size()))
//	                .imageUrl(imageUrls[i])
//	                .startDate(startDates[i])
//	                .endDate(endDates[i])
//	                .build();
//	        
//	        advertisements.add(advertisement);  
//	    }
//	    advertisementRepository.saveAll(advertisements); 
//	}
//	@Test
//	@Rollback(false)
//	@DisplayName("Advertisement 저장")
//	void saveJobPosting() {
//		List<Company> companies = companyRepository.findAll();
//
//		
//		if (companies.isEmpty()) {
//			throw new IllegalStateException("저장된 Company 데이터가 없습니다! 먼저 Company 데이터를 추가하세요.");
//		}
//
//		String[] titles = { "Software Engineer", "Data Scientist", "UX Designer", "Product Manager",
//				"Backend Developer", "Frontend Developer", "AI Researcher", "Cloud Engineer", "DevOps Engineer",
//				"Cybersecurity Analyst" };
//
//		String[] contents = { "혁신적인 기술을 개발할 엔지니어를 찾습니다.", "데이터 분석 및 AI 모델 개발 업무를 담당합니다.", "사용자 경험을 최적화하는 디자인 업무입니다.",
//				"제품 전략을 기획하고 실행하는 역할입니다.", "서버 및 API 개발을 담당하는 포지션입니다.", "웹 애플리케이션 프론트엔드 개발을 진행합니다.",
//				"AI 연구 및 알고리즘 개발을 수행합니다.", "클라우드 기반 솔루션을 개발하는 업무입니다.", "CI/CD 및 서버 운영을 담당하는 포지션입니다.",
//				"보안 정책 수립 및 해킹 대응 업무를 수행합니다." };
//		
//		String[] jobs = {
//			    "소프트웨어 개발",
//			    "데이터 분석 및 AI",
//			    "UX/UI 디자인",
//			    "프로덕트 매니지먼트",
//			    "백엔드 개발",
//			    "프론트엔드 개발",
//			    "AI 연구 및 머신러닝",
//			    "클라우드 및 인프라 엔지니어링",
//			    "DevOps 엔지니어링",
//			    "사이버 보안 및 정보 보호"
//			};
//
//
//		String[] requirements = { "Java, Spring 경험자 우대", "Python, TensorFlow 활용 가능자", "Figma, Adobe XD 사용 가능자",
//				"PM 경험 3년 이상", "REST API 개발 경험 필수", "React, Vue 개발 경험자", "머신러닝 관련 연구 경험", "AWS 및 클라우드 서비스 경험",
//				"CI/CD 구축 경험", "보안 인증 및 침해 대응 경험" };
//
//		String[] workingHours = { "09:00 - 18:00", "유연 근무제 가능", "출퇴근 자유", "풀타임 근무", "주 4일 근무 가능", "원격 근무 가능",
//				"하이브리드 근무", "야간 근무 포함", "주말 근무 가능", "프로젝트 기반 일정 조율" };
//
//		String[] workLocations = { "서울 강남", "서울 마포", "부산 해운대", "대전 서구", "대구 중구", "광주 북구", "인천 송도", "제주 서귀포", "경기 판교",
//				"충북 청주" };
//
//		String[] careerTypes = { "신입", "경력", "경력", "경력", "신입", "경력", "신입", "경력", "신입", "경력" };
//
//	Integer[] salaries = { 50000000, 60000000, 70000000, 55000000, 45000000, 60000000, 70000000, 80000000, 40000000,
//			65000000 };
//
//	LocalDate[] createdDates = { LocalDate.of(2025, 5, 1), LocalDate.of(2025, 5, 3), LocalDate.of(2025, 5, 5),
//			LocalDate.of(2025, 5, 7), LocalDate.of(2025, 5, 9), LocalDate.of(2025, 5, 11), LocalDate.of(2025, 5, 13),
//			LocalDate.of(2025, 5, 15), LocalDate.of(2025, 5, 17), LocalDate.of(2025, 5, 19) };
//
//	LocalDate[] expiredDates = { LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 3), LocalDate.of(2025, 6, 5),
//			LocalDate.of(2025, 6, 7), LocalDate.of(2025, 6, 9), LocalDate.of(2025, 6, 11), LocalDate.of(2025, 6, 13),
//			LocalDate.of(2025, 6, 15), LocalDate.of(2025, 6, 17), LocalDate.of(2025, 6, 19) };
//	
//	if (titles.length != contents.length || 
//	        titles.length != requirements.length || 
//	        titles.length != careerTypes.length ||
//	        titles.length != salaries.length || 
//	        titles.length != createdDates.length || 
//	        titles.length != expiredDates.length) {
//	        
//	        throw new IllegalStateException("모든 배열의 개수가 일치해야 합니다.");
//	    }
//
//
//	List<JobPosting> jobPostings = new ArrayList<>();
//
//	for (int i = 0; i < titles.length; i++) {
//       
//				JobPosting jobPosting = JobPosting.builder()	
//                .company(companies.get(i % companies.size()))
//                .title(titles[i])
//                .content(contents[i])
//                .job(jobs[i])
//                .requirement(requirements[i])
//                .workingHours(workingHours[i])
//                .workLocation(workLocations[i])
//                .careerType(careerTypes[i])
//                .educationLevel(
//                    (i % 5 == 0) ? EducationLevel.고등학교졸업 :
//                    (i % 5 == 1) ? EducationLevel.전문학사 :
//                    (i % 5 == 2) ? EducationLevel.학사 :
//                    (i % 5 == 3) ? EducationLevel.석사 :
//                                   EducationLevel.박사
//                )
//                .salary(salaries[i])
//                .createdDate(createdDates[i])
//                .expiredDate(expiredDates[i])
//                .build();
//        
//        jobPostings.add(jobPosting);
//    }
//
//	jobPostingRepository.saveAll(jobPostings);
//	}
//}
//	@Test
//	@Rollback(false)
//	@DisplayName("JobPostingImage 저장")
//	void saveJobPostingImage() {
//		List<JobPosting> jobPostings = jobPostingRepository.findAll();
//		
//		String[] imageUrls = {
//			    "https://example.com/images/job1.jpg",
//			    "https://example.com/images/job2.jpg",
//			    "https://example.com/images/job3.jpg",
//			    "https://example.com/images/job4.jpg",
//			    "https://example.com/images/job5.jpg",
//			    "https://example.com/images/job6.jpg",
//			    "https://example.com/images/job7.jpg",
//			    "https://example.com/images/job8.jpg",
//			    "https://example.com/images/job9.jpg",
//			    "https://example.com/images/job10.jpg"
//			};
//		List<JobPostingImage> jobPostingImages = new ArrayList<>();
//
//		 for (int i = 0; i < imageUrls.length; i++) {
//		        JobPostingImage jobPostingImage = JobPostingImage.builder()
//		        		.jobPosting(jobPostings.get(i % jobPostings.size()))
//		                .imageUrl(imageUrls[i])
//		                .build();
//
//		        jobPostingImages.add(jobPostingImage);
//		    }
//		 jobPostingImageRepository.saveAll(jobPostingImages);
//	}
//}	
//	@Test
//	@DisplayName("Industry 조회")
//	void findIndustry() {
//		    // ✅ 업종 데이터 조회
//		    List<Industry> industries = industryRepository.findAll();
//
//		    // ✅ 조회된 데이터 개수 검증 (예: 최소 1개 이상 있어야 함)
//		    assertFalse(industries.isEmpty(), "저장된 Industry 데이터가 없습니다!");
//
//		    // ✅ 콘솔 출력
//		    industries.forEach(industry -> {
//		        System.out.println("Industry ID: " + industry.getId());
//		        System.out.println("Industry Name: " + industry.getIndustryName());
//		        System.out.println("Description: " + industry.getDescription());
//
//		   
//		        System.out.println("Companies:");
//		        industry.getCompanies().forEach(company -> 
//		        System.out.println("   - Company ID: " + company.getId() + "\n"+
//                        ", Name: " + company.getCompanyName()  +  "\n"+
//                        ", Address: " + company.getCompanyAddress() +  "\n"+
//                        ", Phone: " + company.getCompanyPhone() + "\n"+
//                        ", Employees: " + company.getEmployeeCount() + "\n"+
//                        ", Established: " + company.getEstablishedDate() + "\n"+
//                        ", Introduction: " + company.getIntroduction() + "\n"+
//                        ", Representative: " + company.getRepresentativeName() + "\n"+
//                        ", Type: " + company.getType())
// );
//
//
//		        System.out.println("------------------------------");
//		    });
//		}

//	@Test
//	@DisplayName("Company 조회")
//	void findCompany() {
//		List<Long> companyIds = List.of(11L, 12L, 13L, 14L, 15L, 16L, 17L, 18L, 19L, 20L); 
//		companyIds.forEach(id -> {
//		    Optional<Company> companyOptional = companyRepository.findById(id);
//		    if (companyOptional.isPresent()) {
//		        Company company = companyOptional.get();
//		        System.out.println("Company ID: " + company.getId());
//		        System.out.println("Name: " + company.getCompanyName());
//		        System.out.println("Address: " + company.getCompanyAddress());
//		        System.out.println("업종명: "+ company.getIndustry().getIndustryName());
//		        System.out.println("------------------------------");
//		    } else {
//		        System.out.println("기업 ID " + id + "에 해당하는 데이터가 없습니다.");
//		    }
//		});
//
//	}
//	@Test
//	@DisplayName("Advertisement 조회")
//	void findAdvertisement() {
//	    List<Advertisement> advertisements = advertisementRepository.findAll(); // ✅ 모든 광고 조회
//
//	    advertisements.forEach(ad -> { 
//	        System.out.println("Advertisement ID: " + ad.getId());
//	        System.out.println("Image URL: " + ad.getImageUrl());
//	        System.out.println("Start Date: " + ad.getStartDate());
//	        System.out.println("End Date: " + ad.getEndDate());
//	        System.out.println("------------------------------");
//	    });
//	}
	//@Test
//	@DisplayName("JobPosting 채용공고목록조회")
//	void findJobPostings() {
//		
//	    Long companyId = 11L; // ✅ 테스트할 기업 ID
//	    Pageable pageable = PageRequest.of(0, 10);
//	    
//	    
//		Page<JobPosting> jobPostings = companyJobPostingRepository.findAllByCompanyId(companyId, pageable);
//		long totalCount = companyJobPostingRepository.countJobPostingsByCompanyId(companyId);
//	    
//	    jobPostings.forEach(posting -> {
//	        System.out.println("Job Posting ID: " + posting.getId());
//	        System.out.println("Title: " + posting.getTitle());
//	        System.out.println("Created Date: " + posting.getCreatedDate());
//	        System.out.println("🔢 Total Job Postings: " + jobPostings.getTotalElements()); 
//	        System.out.println("------------------------------");
//	    });
//	    
//	}
//	@DisplayName("JobPosting 채용공고단건조회")
//	void findJobPostingWithImage() {
//	
//		 Long jobPostingId = 5L;
//		 
//		 Optional<JobPosting> jobPostingWithImage = 
//		 companyJobPostingRepository.findWithJobPostingImageByJobPostingId(jobPostingId);
//		 System.out.println("==============================");
//	        System.out.println("Job Posting ID: " + jobPostingId);
//	        jobPostingWithImage.ifPresent(posting -> {
//	            System.out.println("Title: " + posting.getTitle());
//	            System.out.println("Created Date: " + posting.getCreatedDate());
//	            if (!posting.getJobPostingImages().isEmpty()) {
//	                System.out.println("Images Count: " + posting.getJobPostingImages().size());
//	                posting.getJobPostingImages().forEach(image -> 
//	                    System.out.println("  이미지 주소 : " + image.getImageUrl()));
//	            } else {
//	                System.out.println(" 이미지가 없습니다.");
//	            }
//
//	        });
//	        System.out.println("==============================");
//	}
//	@DisplayName("채용공고단건조회(지원리스트)")
//	void findJobPostingWithApplication() {
//		Long jobPostingId = 5L;
//		
//		Optional<JobPosting> jobPostingWithMember = companyJobPostingRepository
//				.findwithApplicationsByJobPostingId(jobPostingId);
//		 System.out.println("==============================");
//		 System.out.println("Job Posting ID: " + jobPostingId); 
//		
//		 jobPostingWithMember.ifPresent(posting -> {
//	            System.out.println("Title: " + posting.getTitle());
//	            System.out.println("Created Date: " + posting.getCreatedDate());
//	            if (!posting.getApplications().isEmpty()) {
//	                System.out.println("👥 Applicant Count: " + posting.getApplications().size()); // ✅ 지원자 수 출력
//	                posting.getApplications().forEach(ap -> 
//	                    System.out.println("  📝 Applicant ID: " + ap.getId() + ", Name: " + ap.getId())); // ✅ 지원자 정보 출력
//	            } else {
//	                System.out.println("지원자가 없습니다."); 
//	            }
//	        });
//
//	        System.out.println("==============================");
//	    }
//
//	            
//} 
		 
	
		 
	    
	


