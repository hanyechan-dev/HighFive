package com.jobPrize.repository.common;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import com.jobPrize.config.QuerydslConfig;
import com.jobPrize.entity.member.Career;
import com.jobPrize.entity.member.Certification;
import com.jobPrize.entity.member.Education;
import com.jobPrize.entity.member.LanguageTest;
import com.jobPrize.entity.member.Member;
import com.jobPrize.entity.member.Resume;
import com.jobPrize.repository.company.Industry.IndustryRepository;
import com.jobPrize.repository.company.company.CompanyRepository;
import com.jobPrize.repository.consultant.consultant.ConsultantRepository;
import com.jobPrize.repository.member.career.CareerRepository;
import com.jobPrize.repository.member.careerDescription.CareerDescriptionRepository;
import com.jobPrize.repository.member.careerDescriptionContent.CareerDescriptionContentRepository;
import com.jobPrize.repository.member.certification.CertificationRepository;
import com.jobPrize.repository.member.coverLetter.CoverLetterRepository;
import com.jobPrize.repository.member.coverLetterContent.CoverLetterContentRepository;
import com.jobPrize.repository.member.education.EducationRepository;
import com.jobPrize.repository.member.languageTest.LanguageTestRepository;
import com.jobPrize.repository.member.member.MemberRepository;
import com.jobPrize.repository.member.resume.ResumeRepository;

import jakarta.persistence.EntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(QuerydslConfig.class)
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private ConsultantRepository consultantRepository;

	@Autowired
	private ResumeRepository resumeRepository;

	@Autowired
	private EducationRepository educationRepository;

	@Autowired
	private CareerRepository careerRepository;

	@Autowired
	private CertificationRepository certificationRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private IndustryRepository industryRepository;

	@Autowired
	private LanguageTestRepository languageTestRepository;

	@Autowired
	private CoverLetterRepository coverLetterRepository;
	
	@Autowired
	private CoverLetterContentRepository coverLetterContentRepository;
	
	@Autowired
	private CareerDescriptionRepository careerDescriptionRepository;
	
	@Autowired
	private CareerDescriptionContentRepository careerDescriptionContentRepository;

	@Autowired
	private EntityManager em;

	
//	@Test // 완료
//	@Rollback(false)
//	@DisplayName("User 및 Member 저장")
//	void saveUserAndMember() {
//		String[] names = {"강태엽", "박한마", "길탁", "이성진", "이후락", "진정식", "김신", "하후성", "백도찬", "김태훈"};
//		String[] emails = {"kang", "park", "gil", "lee", "rak", "jin", "kim","ha","baek", "hoon"};
//		String[] sidos = {"서울", "부산", "인천", "대전", "수원", "광주", "울산", "제주", "청주", "원주"};
//		String[] nickNames = {"철권", "붉은다리", "이소룡", "복서", "정복자", "제왕", "신", "황소", "백사", "민첩"};
//		for(int i=0;i<10;i++) {
//			User user = User.builder()
//					.email(emails[i]+"@user.com")
//					.password("pass1234")
//					.name(names[i])
//					.phone("0101234" + String.format("%04d", i))
//					.address(sidos[i])
//					.type(UserType.일반회원)
//					.build();
//
//			userRepository.save(user);
//			Member member = Member.builder()
//					.user(user)
//					.nickname(nickNames[i])
//					.build();
//			memberRepository.save(member);
//		}
//		em.flush();
//		em.clear();
//	}

//	@Test
//	@Rollback(false)
//	@DisplayName("Member에 Resume 저장")
//	void saveResume() {
//		for (int i = 1; i <= 10; i++) {
//			Member member = memberRepository.findById(Long.valueOf(i)).orElseThrow(() -> new IllegalArgumentException("해당 ID의 멤버가 존재하지 않음"));
//			Resume resume = Resume.builder().member(member).build();
//			
//			resumeRepository.save(resume);
//		}
//		em.flush();
//		em.clear();
//	}

	
//	@Test
//	@Rollback(false)
//	@DisplayName("Resume에 Education 저장")
//	void saveEducation() {
//		Random random = new Random();
//		String[] highSchoolNames= {"서울예술", "서울과학", "대원외국어", "경기과학", "하나", "성산", "민족사관", "대구과학", "대전과학", "선화예술"};
//		String[] collegeNames= {"영진", "대림", "유한", "명지", "동아방송예술", "연암공과", "서울예술", "인하공업", "동양미래", "농협"};
//		String[] universityNames= {"서울", "연세", "고려", "성규관", "한양", "부산", "경북", "제주", "전북", "중앙"};
//		String[] sidos = {"서울", "부산", "인천", "대전", "수원", "광주", "울산", "제주", "청주", "원주"};
//		String[] majors = {"컴퓨터공학", "경영학", "기계공학", "화학공학", "심리학", "간호학", "사회복지학", "식품영약학", "철학", "국제무역학"};
//		
//		for (int i = 1; i <= 10; i++) {
//			Resume resume = resumeRepository.findById(Long.valueOf(i)).orElseThrow(() -> new IllegalArgumentException("해당 ID의 이력서가 존재하지 않음"));
//			int randomNo1 = random.nextInt(4)+1;
//			boolean randomBoolean = random.nextBoolean();
//			for(int j=0 ; j<randomNo1;j++) {
//				if(j==0) {
//					Education education = Education
//							.builder()
//							.resume(resume)
//							.schoolName(highSchoolNames[random.nextInt(10)]+"고등학교")
//							.educationLevel(EducationLevel.고등학교졸업)
//							.location(sidos[random.nextInt(10)])
//							.enterDate(LocalDate.of(2000+j*2, 3, 2))
//							.graduateDate(LocalDate.of(2003+j*2, 2, 22))
//							.build();
//					
//					educationRepository.save(education);
//				}
//				else if(j==1) {
//					if(randomBoolean) {
//						Education education = Education
//								.builder()
//								.resume(resume)
//								.schoolName(collegeNames[random.nextInt(10)]+"전문대학")
//								.educationLevel(EducationLevel.전문학사)
//								.location(sidos[random.nextInt(10)])
//								.gpa(BigDecimal.valueOf(Math.round(Math.random() * 450) / 100.0))
//								.major(majors[random.nextInt(10)])
//								.enterDate(LocalDate.of(2003+j*2, 3, 2))
//								.graduateDate(LocalDate.of(2005+j*2, 2, 22))
//								.build();
//						
//						educationRepository.save(education);
//					}
//					else {
//						Education education = Education
//								.builder()
//								.resume(resume)
//								.schoolName(universityNames[random.nextInt(10)]+"대학교")
//								.educationLevel(EducationLevel.학사)
//								.location(sidos[random.nextInt(10)])
//								.gpa(BigDecimal.valueOf(Math.round(Math.random() * 450) / 100.0))
//								.major(majors[random.nextInt(10)])
//								.enterDate(LocalDate.of(2003+j*2, 3, 2))
//								.graduateDate(LocalDate.of(2006+j*2, 2, 22))
//								.build();
//						
//						educationRepository.save(education);
//					}
//				
//					
//				}
//				else if(j==2) {
//					Education education = Education
//							.builder()
//							.resume(resume)
//							.schoolName(universityNames[random.nextInt(10)]+"대학교")
//							.educationLevel(EducationLevel.석사)
//							.location(sidos[random.nextInt(10)])
//							.gpa(BigDecimal.valueOf(Math.round(Math.random() * 450) / 100.0))
//							.major(majors[random.nextInt(10)])
//							.enterDate(LocalDate.of(2006+j*2, 3, 2))
//							.graduateDate(LocalDate.of(2008+j*2, 2, 22))
//							.build();
//					
//					educationRepository.save(education);
//				}
//				else if(j==3) {
//					Education education = Education
//							.builder()
//							.resume(resume)
//							.schoolName(universityNames[random.nextInt(10)]+"대학교")
//							.educationLevel(EducationLevel.박사)
//							.location(sidos[random.nextInt(10)])
//							.gpa(BigDecimal.valueOf(Math.round(Math.random() * 450) / 100.0))
//							.major(majors[random.nextInt(10)])
//							.enterDate(LocalDate.of(2008+j*2, 3, 2))
//							.graduateDate(LocalDate.of(2015+j*2, 2, 22))
//							.build();
//					
//					educationRepository.save(education);
//				}	
//			}
//		}
//		em.flush();
//		em.clear();
//	}

	
//	@Test
//	@Rollback(false)
//	@DisplayName("Resume에 Career 저장")
//	void saveCareer() {
//		Random random = new Random();
//		String[] companyNames = { "한빛소프트", "카카오모빌리티", "LG CNS", "쿠팡", "뷰노", "삼성 SDS", "토스페이먼츠", "더존비즈온", "컬리",
//				"네이버클라우드" };
//		String[] departmentNames = { "인사", "재무회계", "마케팅", "개발", "기획", "영업", "고객지원", "품질관리", "디자인", "전략기획" };
//		String[] jobNames = { "프론트엔드 개발", "백엔드 개발", "데이터 분석가", "UX/UI 설계", "기획", "마케팅", "인사", "회계", "고객지원", "품질관리" };
//		String[] Positions = { "사원", "주임", "대리", "과장", "차장", "부장" };
//
//		for (int i = 1; i <= 10; i++) {
//			Resume resume = resumeRepository.findById(Long.valueOf(i))
//					.orElseThrow(() -> new IllegalArgumentException("해당 ID의 이력서가 존재하지 않음"));
//			int randomNo1 = random.nextInt(5) + 1;
//			for (int j = 0; j < randomNo1; j++) {
//
//				Career career = Career.builder()
//						.resume(resume)
//						.companyName(companyNames[random.nextInt(10)])
//						.department(departmentNames[random.nextInt(10)])
//						.job(jobNames[random.nextInt(10)])
//						.position(Positions[random.nextInt(6)])
//						.startDate(LocalDate.of(2020 + j * 2, 3, 2))
//						.endDate(LocalDate.of(2022 + j * 2, 2, 22))
//						.build();
//
//				careerRepository.save(career);
//
//			}
//		}
//		em.flush();
//		em.clear();
//	}

	
//	@Test
//	@Rollback(false)
//	@DisplayName("Resume에 Certification 저장")
//	void saveCertification() {
//		Random random = new Random();
//		String[] certificationNames = { "정보처리기사", "SQLD", "네트워크관리사", "컴퓨터활용능력", "GTQ", "전기기사", "산업안전기사", "기계설계산업기사", "건축기사", "소방설비기사" };
//		String[] issuingOrgs = { "한국산업인력공단", "한국데이터산업진흥원", "한국정보통신자격협회", "대한상공회의소", "한국생산성본부", "한국산업인력공단", "한국산업안전보건공단", "한국산업인력공단", "한국산업인력공단", "한국산업인력공단" };
//		String[] grades = { null, null, random.nextInt(2)+1+"급", random.nextInt(2)+1+"급", random.nextInt(2)+1+"급", null, null, null, null, null};
//		String[] scores = { null, null, String.valueOf(random.nextInt(100)), String.valueOf(random.nextInt(800)), String.valueOf(random.nextInt(100)), String.valueOf(random.nextInt(100)), String.valueOf(random.nextInt(100)), null, null, null };
//
//		for (int i = 1; i <= 10; i++) {
//			Resume resume = resumeRepository.findById(Long.valueOf(i))
//					.orElseThrow(() -> new IllegalArgumentException("해당 ID의 이력서가 존재하지 않음"));
//			int randomNo1 = random.nextInt(5) + 1;
//			for (int j = 0; j < randomNo1; j++) {
//				int randomNo2 = random.nextInt(10);
//				Certification certification = Certification.builder()
//						.resume(resume)
//						.certificationName(certificationNames[randomNo2])
//						.issuingOrg(issuingOrgs[randomNo2])
//						.grade(grades[randomNo2])
//						.score(scores[randomNo2])
//						.certificationNo("1111-11-11-"+i)
//						.acquisitionDate(LocalDate.now())
//						.build();
//
//				certificationRepository.save(certification);
//
//			}
//		}
//		em.flush();
//		em.clear();
//	}

	
//	@Test
//	@Rollback(false)
//	@DisplayName("Resume에 LanguageTest 저장")
//	void saveLanguageTest() {
//		Random random = new Random();
//		String[] testNames = { "TOEIC", "TOEFL", "IELTS", "OPIc", "TEPS", "HSK", "JLPT", "DELF", "DELE", "BCT" };
//		String[] languageTypes = { "영어", "영어", "영어", "영어", "영어", "중국어", "일본어", "프랑스어", "스페인어", "중국어" };
//		String[] issuingOrgs = { "ETS", "ETS", "British Council / IDP / Cambridge", "ACTFL", "서울대학교 언어교육원", "한중교류문화원", "일본국제교류기금", "프랑스 교육부", "스페인 교육문화체육부", "중국 상무부" };
//		String[] grades = { null, null, null, null, null, String.valueOf(random.nextInt(6)+1)+"급", "N"+String.valueOf(random.nextInt(5)+1),
//				String.valueOf((char)('A' + random.nextInt(3)))+ random.nextInt(2)+1, String.valueOf((char)('A' + random.nextInt(3)))+ random.nextInt(2)+1, String.valueOf((char)('A' + random.nextInt(3)))};
//		String[] scores = { String.valueOf(random.nextInt(981)+10), String.valueOf(random.nextInt(121)), String.valueOf(random.nextInt(9)+1), String.valueOf(random.nextInt(301)), String.valueOf(random.nextInt(791)+200), null, null, null, null, null };
//
//		for (int i = 1; i <= 10; i++) {
//			Resume resume = resumeRepository.findById(Long.valueOf(i))
//					.orElseThrow(() -> new IllegalArgumentException("해당 ID의 이력서가 존재하지 않음"));
//			int randomNo1 = random.nextInt(5) + 1;
//			for (int j = 0; j < randomNo1; j++) {
//				int randomNo2 = random.nextInt(10);
//				LanguageTest languageTest = LanguageTest.builder()
//						.resume(resume)
//						.languageType(languageTypes[randomNo2])
//						.testName(testNames[randomNo2])
//						.issuingOrg(languageTypes[randomNo2])
//						.grade(grades[randomNo2])
//						.score(scores[randomNo2])
//						.certificationNo("1111-11-11-"+i)
//						.acquisitionDate(LocalDate.now())
//						.build();
//
//				languageTestRepository.save(languageTest);
//
//			}
//
//		}
//		em.flush();
//		em.clear();
//	}


	
//    @Test
//    @Rollback(false)
//    @DisplayName("Member에 CoverLetter 저장")
//    void saveCoverLetter() {
//    	
//    	Random random = new Random();
//    	String[] titles = {
//    		    "도전을 두려워하지 않는 열정",
//    		    "팀워크로 이룬 프로젝트 경험",
//    		    "문제 해결에 강한 엔지니어",
//    		    "기술과 소통의 가교 역할",
//    		    "성실함으로 다져진 기본기",
//    		    "끊임없는 배움의 자세",
//    		    "현장에서 빛나는 실전형 인재",
//    		    "사용자를 생각하는 개발자",
//    		    "데이터로 말하는 분석가",
//    		    "조직과 함께 성장하는 사람"
//    		};
//        for(int i=1;i<=10;i++) {
//        	Member member = memberRepository.findById(Long.valueOf(i)).orElseThrow(() -> new IllegalArgumentException("해당 ID의 멤버가 존재하지 않음"));
//        	
//        	for(int j=0;j<random.nextInt(5)+1;j++) {
//        		CoverLetter coverLetter = CoverLetter
//            			.builder()
//            			.title(titles[random.nextInt(10)])
//            			.member(member)
//            			.build();
//                
//            	
//            	coverLetterRepository.save(coverLetter);
//        	}
//        }
//        em.flush();
//        em.clear();
//    }

	
//	@Test
//	@Rollback(false)
//	@DisplayName("CoverLetter에 내용 저장")
//	void saveCoverLetterContent() {
//
//		Random random = new Random();
//		String[] contents = { "팀 프로젝트에서 백엔드 개발을 맡아 안정적인 API를 구현했습니다.", "문제 해결을 위해 직접 로그를 분석하고 원인을 찾아 개선했습니다.",
//				"고객의 피드백을 반영해 UI/UX를 개선한 경험이 있습니다.", "협업 중 발생한 충돌을 중재하고 일정 내 프로젝트를 마무리했습니다.",
//				"매일 코드 리뷰를 통해 품질 향상과 동료 성장에 기여했습니다.", "다양한 언어와 프레임워크를 학습해 빠르게 실무에 투입되었습니다.",
//				"기획부터 배포까지 전체 과정을 주도하며 서비스를 런칭했습니다.", "데이터 분석을 통해 마케팅 전략 수립에 기여했습니다.",
//				"자동화 도구를 도입해 반복 업무를 줄이고 효율을 높였습니다.", "사용자 중심의 기능 개선으로 이용률을 20% 향상시켰습니다." };
//
//		String[] items = { "직무 경험", "문제 해결 경험", "고객의 목소리를 들은 경험", "커뮤니케이션 경험", "성장을 위한 경험", "실무 경험",
//				"주도적으로 프로젝트를 완성한 경험", "완전히 새로운 기술을 습득하여 업무에 적용한 경험", "업무 효율을 증대 시킨 경험", "고객처럼 생각한 경험" };
//
//		List<CoverLetter> coverLetters = coverLetterRepository.findAll();
//
//		for (CoverLetter coverLetter : coverLetters) {
//			
//			for(int i=0;i<random.nextInt(5)+1;i++) {
//				int randomNumber = random.nextInt(10);
//				CoverLetterContent coverLetterContent = CoverLetterContent
//						.builder()
//						.coverLetter(coverLetter)
//						.item(items[randomNumber])
//						.content(contents[randomNumber])
//						.build();
//				
//				coverLetterContentRepository.save(coverLetterContent);
//			}
//			
//		}
//		em.flush();
//		em.clear();
//	}
	
	
//  @Test
//  @Rollback(false)
//  @DisplayName("Member에 CareerDescription 저장")
//  void saveCareerDescription() {
//  	
//  	Random random = new Random();
//  	String[] titles = {
//  			"백엔드 시스템 아키텍처 설계 및 구현 경험"
//  			,"실시간 데이터 처리 플랫폼 구축 사례"
//  			,"RESTful API 설계 및 성능 최적화 경험"
//  			,"CI/CD 파이프라인 도입으로 배포 자동화"
//  			,"JPA 기반 대용량 데이터 처리 프로젝트"
//  			,"보안 취약점 진단 및 대응 경험"
//  			,"AWS 인프라 환경에서의 서비스 운영 경험"
//  			,"다국어 지원 웹서비스 개발 프로젝트"
//  			,"프로젝트 리더로서의 일정 관리 및 팀 운영"
//  			,"사용자 피드백 기반 기능 개선 사례"
//  		};
//      for(int i=1;i<=10;i++) {
//      	Member member = memberRepository.findById(Long.valueOf(i)).orElseThrow(() -> new IllegalArgumentException("해당 ID의 멤버가 존재하지 않음"));
//      	
//      	for(int j=0;j<random.nextInt(5)+1;j++) {
//      		CareerDescription careerDescription = CareerDescription
//          			.builder()
//          			.title(titles[random.nextInt(10)])
//          			.member(member)
//          			.build();
//              
//          	
//      		careerDescriptionRepository.save(careerDescription);
//      	}

//      }
//      em.flush();
//      em.clear();
//  }
	
//	@Test
//	@Rollback(false)
//	@DisplayName("CareerDescription에 내용 저장")
//	void saveCareerDescriptionContent() {
//
//		Random random = new Random();
//		String[] contents = {"Spring Boot 기반 REST API 개발 및 MySQL 연동",
//				"PG사 연동 및 결제 성공률 15% 개선",
//				"Redis 캐싱 도입으로 평균 응답속도 40% 단축",
//				"실무 전반에 걸쳐 안정적으로 활용",
//				"Swagger로 API 문서화하여 커뮤니케이션 효율 증가",
//				"DB deadlock 원인 분석 후 트랜잭션 구조 개선",
//				"코드 복잡도 감소 및 유지보수 용이성 증가",
//				"JUnit, Mockito로 커버리지 80% 이상 확보",
//				"AWS EC2 + CodeDeploy 사용",
//				"리뷰 문서 작성 및 정기 코드 리뷰 주도" };
//
//		String[] items = { "사내 ERP 시스템 백엔드 개발", "결제 모듈 구현", "서비스 응답속도 개선", " Java, Spring Boot, JPA", "프론트엔드 팀과 협업", "장애 로그 분석 및 대응",
//				"레거시 코드 리팩토링", "단위 테스트 및 통합 테스트 도입", "무중단 배포 구현", "주니어 개발자 코드 리뷰" };
//
//		List<CareerDescription> careerDescriptions = careerDescriptionRepository.findAll();
//
//		for (CareerDescription careerDescription : careerDescriptions) {
//			
//			for(int i=0;i<random.nextInt(5)+1;i++) {
//				int randomNumber = random.nextInt(10);
//				CareerDescriptionContent careerDescriptionContent = CareerDescriptionContent
//						.builder()
//						.careerDescription(careerDescription)
//						.item(items[randomNumber])
//						.content(contents[randomNumber])
//						.build();
//				
//				careerDescriptionContentRepository.save(careerDescriptionContent);
//			}
//			
//		}
//		em.flush();
//		em.clear();		
//	}
//}

@Test
@Rollback(false)
@DisplayName("CareerDescription에 내용 저장")
void findMemberWithAll() {
	Member member = memberRepository.findWithAllDocumentsByMemberId(null).orElseThrow(() -> new IllegalArgumentException("해당 ID의 멤버가 존재하지 않음"));
	String nickname = member.getNickname();
	Resume resume = member.getResume();
	List<Education> educations = resume.getEducations();
	List<Career> careers= resume.getCareers();	
	List<Certification> certifications= resume.getCertifications();
	List<LanguageTest> languageTests= resume.getLanguageTests();
	
	
	
	
	
}	
}