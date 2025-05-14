package com.jobPrize.repository.common;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import com.jobPrize.config.QuerydslConfig;
import com.jobPrize.entity.member.Certification;
import com.jobPrize.entity.member.Resume;
import com.jobPrize.repository.company.Industry.IndustryRepository;
import com.jobPrize.repository.company.company.CompanyRepository;
import com.jobPrize.repository.consultant.consultant.ConsultantRepository;
import com.jobPrize.repository.member.career.CareerRepository;
import com.jobPrize.repository.member.certification.CertificationRepository;
import com.jobPrize.repository.member.education.EducationRepository;
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
	private EntityManager em;

//    @Test
//    @Rollback(false)
//    @DisplayName("User 저장")
//    void saveUser() {
//        for(int i=0;i<10000;i++) {
//        	User user = User.builder()
//                    .email("test"+i+"@user.com")
//                    .password("pass1234")
//                    .name("홍길동")
//                    .phone("0101234"+String.format("%04d", i))
//                    .address("서울특별시")
//                    .type(UserType.일반회원)
//                    .build();
//
//            userRepository.save(user);
//        	
//        }
//        
//        em.flush();
//        em.clear();
//
//    }

//    @Test
//    @Rollback(false)
//    @DisplayName("User 및 Member 저장")
//    void saveUserAndMember() {
//        for(int i=0;i<1000;i++) {
//        	User user = User
//        			.builder()
//        			.email("memberTest"+i+"@user.com")
//                    .password("pass1234")
//                    .name("홍길동")
//                    .phone("0101234"+String.format("%04d", i))
//                    .address("서울특별시")
//                    .type(UserType.일반회원)
//                    .build();
//
//            userRepository.save(user);
//            
//            Member member = Member
//            		.builder()
//            		.user(user)
//            		.nickname("김탁봉")
//            		.build();
//            
//            memberRepository.save(member);
//        	
//        }
//        
//        em.flush();
//        em.clear();
//
//    }
	
//
//    @Test
//    @Rollback(false)
//    @DisplayName("User 및 Consultant 저장")
//    void saveUserAndConsultant() {
//        for(int i=0;i<1000;i++) {
//        	User user = User
//        			.builder()
//        			.email("consultantTest"+i+"@user.com")
//                    .password("pass1234")
//                    .name("김컨설")
//                    .phone("0101234"+String.format("%04d", i))
//                    .address("서울특별시")
//                    .type(UserType.컨설턴트회원)
//                    .build();
//
//            userRepository.save(user);
//            
//           Consultant consultant = Consultant
//            		.builder()
//            		.user(user)
//            		.build();
//            
//            consultantRepository.save(consultant);
//        	
//        }
//        
//        em.flush();
//        em.clear();
//
//    }
	
//  @Test
//  @Rollback(false)
//  @DisplayName("Industry 저장")
//  void saveIndustry() {
//      for(int i=0;i<20;i++) {
//      	Industry industry = Industry
//      			.builder()
//      			.industryName("산업"+i)
//      			.description("산업"+i+"에 대한 설명")
//                 .build();
//      	
//      	industryRepository.save(industry);
//     
//      }
//      
//      em.flush();
//  }

//   @Test
//   @Rollback(false)
//   @DisplayName("User 및 Company 저장")
//   void saveUserAndConsultnat() {
//       for(int i=0;i<1000;i++) {
//       	User user = User
//       			.builder()
//       			.email("CompanyTest"+i+"@user.com")
//                   .password("pass1234")
//                   .name("박인사")
//                   .phone("0101234"+String.format("%04d", i))
//                   .address("대전광역시")
//                   .type(UserType.기업회원)
//                   .build();
//
//           userRepository.save(user);
//           
//           Industry industry = industryRepository.findById(Long.valueOf(i%20+1)).orElseThrow(() -> new IllegalArgumentException("해당 ID의 멤버가 존재하지 않음"));
//           
//          Company company = Company
//           		.builder()
//           		.companyName("기업"+i)
//           		.representativeName("이대표")
//           		.establishedDate(LocalDate.of(2005, 5, 3))
//           		.businessNumber("111-11-"+String.format("%04d", i))
//           		.companyAddress("동탄")
//           		.type(CompanyType.중견기업)
//           		.industry(industry)
//           		.companyPhone("0104444"+String.format("%04d", i))
//           		.introduction("문화를 선도하는 기업, 빙그레입니다.")
//           		.employeeCount(155)
//           		.user(user)
//           		.build();
//           
//          companyRepository.save(company);
//       	
//       }
//       
//       em.flush();
//       em.clear();
//
//   }


//	@Test
//	@Rollback(false)
//	@DisplayName("Member에 Resume 저장")
//	void saveResume() {
//		for (int i = 10001; i <= 11000; i++) {
//			Member member = memberRepository.findById(Long.valueOf(i)).orElseThrow(() -> new IllegalArgumentException("해당 ID의 멤버가 존재하지 않음"));
//			Resume resume = Resume.builder().member(member).build();
//			
//			resumeRepository.save(resume);
//
//		}
//
//		em.flush();
//		em.clear();
//
//	}

	
//	@Test
//	@Rollback(false)
//	@DisplayName("Resume에 Education 저장")
//	void saveEducation() {
//		for (int i = 1; i <= 1000; i++) {
//			Resume resume = resumeRepository.findById(Long.valueOf(i)).orElseThrow(() -> new IllegalArgumentException("해당 ID의 멤버가 존재하지 않음"));
//			
//			for(int j=1 ; j<=5;j++) {
//				Education education = Education
//						.builder()
//						.resume(resume)
//						.schoolName("학교"+j)
//						.educationLevel(EducationLevel.학사)
//						.major("전공"+j)
//						.gpa(BigDecimal.valueOf(4.33))
//						.location("도시"+j)
//						.enterDate(LocalDate.of(2000+j*5, 3, 2))
//						.graduateDate(LocalDate.of(2005+j*5, 2, j))
//						.build();
//				
//				educationRepository.save(education);
//			}
//
//		}
//
//		em.flush();
//		em.clear();
//
//	}
//	
//	@Test
//	@Rollback(false)
//	@DisplayName("Resume에 Career 정보 저장")
//	void saveCareer() {
//		for (int i = 1; i <= 1000; i++) {
//			Resume resume = resumeRepository.findById(Long.valueOf(i)).orElseThrow(() -> new IllegalArgumentException("해당 ID의 멤버가 존재하지 않음"));
//			
//			for(int j=1 ; j<=5;j++) {
//				Career career = Career
//						.builder()
//						.resume(resume)
//						.companyName("기업"+j)
//						.job("직무"+j)
//						.department("부서"+j)
//						.position("직급"+j)
//						.startDate(LocalDate.of(2000+j*5, 3, 2))
//						.endDate(LocalDate.of(2005+j*5, 3, 2))
//						.build();
//				
//				careerRepository.save(career);
//			}
//
//		}
//
//		em.flush();
//		em.clear();
//
//	}
//	
	@Test
	@Rollback(false)
	@DisplayName("Resume에 Ceritication 정보 저장")
	void saveCeritication() {
		for (int i = 1; i <= 1000; i++) {
			Resume resume = resumeRepository.findById(Long.valueOf(i)).orElseThrow(() -> new IllegalArgumentException("해당 ID의 멤버가 존재하지 않음"));
			
			for(int j=1 ; j<=5;j++) {
				Certification certification = Certification
						.builder()
						.resume(resume)
						.certificationName("자격증"+j)
						.issuingOrg("발급기관"+i)
						.score(String.valueOf(i))
						.acquisitionDate(LocalDate.of(2005+j*5, 3, 2))
						.certificationNo("100000"+i+j)
						.build();
				
				certificationRepository.save(certification);
			}

		}

		em.flush();
		em.clear();

	}
}