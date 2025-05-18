package com.jobPrize.repository.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.jobPrize.config.QuerydslConfig;
import com.jobPrize.repository.consultant.aiConsulting.AiConsultingRepository;
import com.jobPrize.repository.consultant.aiConsultingContent.AiConsultingContentRepository;
import com.jobPrize.repository.consultant.consultant.ConsultantRepository;
import com.jobPrize.repository.consultant.consultantConsulting.ConsultantConsultingRepository;
import com.jobPrize.repository.consultant.consultantConsultingContent.ConsultantConsultingContentRepository;
import com.jobPrize.repository.memToCon.request.RequestRepository;
import com.jobPrize.repository.member.member.MemberRepository;

import jakarta.persistence.EntityManager;




@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(QuerydslConfig.class)
public class ConsultantRepositoryTest {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ConsultantRepository consultantRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private AiConsultingRepository aiConsultingRepository;
	
	@Autowired
	private AiConsultingContentRepository aiConsultingContentRepository;

	@Autowired
	private ConsultantConsultingRepository consultantConsultingRepository;

	@Autowired
	private ConsultantConsultingContentRepository consultantConsultingContentRepository;
	
	@Autowired
	private RequestRepository memberRequestRepository;
	
	
	@Autowired
	private EntityManager em;

//	@Test
//	@Rollback(false)
//	@DisplayName("User 저장")
//	void saveUser() {
//		String[] names = { "김철수", "이영희", "박민수", "최지훈", "정수빈", "한서준", "유하늘", "장민정", "오세훈", "배지은" };
//		String[] emails = { "chulsoo.kim@gmail.com", "younghee.lee@naver.com", "minsoo.park@daum.net",
//				"jihoon.choi@kakao.com", "soobin.jung@nate.com", "seojun.han@outlook.com", "haneul.yoo@icloud.com",
//				"minjung.jang@yahoo.co.kr", "sehoon.oh@toss.im", "jieun.bae@zmail.com" };
//		String[] addresses = { "서울 강남구", "서울 서초구", "서울 송파구", "서울 마포구", "서울 종로구", "부산 해운대구", "대구 수성구", "인천 연수구", "광주 북구",
//				"대전 서구" };
//
//		for (int i = 0; i < 10; i++) {
//			User user = User
//					.builder()
//					.email(emails[i])
//					.password("pass1234")
//					.name(names[i])
//					.phone("0101234" + String
//							.format("%04d", i))
//					.address(addresses[i])
//					.type(UserType.일반회원)
//					.build();
//
//			userRepository.save(user);
//		}
//
//		em.flush();
//		em.clear();
//	}

//	@Test
//	@Rollback(false)
//	@DisplayName("User 및 Member 저장")
//	void saveMembers() {
//	    String[] names = { "김강호", "이지우", "박정석", "최익현", "정조준" };
//	    String[] emails = {
//	        "kim1@job.com",
//	        "lee2@job.com",
//	        "park3@job.com",
//	        "choi4@job.com",
//	        "jung5@job.com"
//	    };
//	    String[] addresses = {
//	        "서울 노원구", "서울 도봉구", "부산 동래구", "대구 북구", "광주 동구"
//	    };
//	    String[] nickname = { "고양이", "강아지", "호랑이", "나비", "소"};
//
//	    for (int i = 0; i < 5; i++) {
//	       
//	        User user = User
//	        	.builder()
//	            .email(emails[i])
//	            .password("pass1234")
//	            .name(names[i])
//	            .phone("0109999" + String.format("%04d", i))
//	            .address(addresses[i])
//	            .type(UserType.일반회원)
//	            .build();
//
//	        userRepository.save(user);
//
//	        Member member = Member
//	        		.builder()
//	                .user(user)
//	                .nickname(nickname[i])
//	                .build();
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
//
//	@Test
//	@Rollback(false)
//	@DisplayName("User 및 Consultant 저장")
//	void saveConsultants() {
//	    String[] names = { "홍지민", "이성훈", "배지수", "정유진", "박찬호" };
//	    String[] emails = {
//	        "consult1@job.com",
//	        "consult2@job.com",
//	        "consult3@job.com",
//	        "consult4@job.com",
//	        "consult5@job.com"
//	    };
//	    String[] addresses = {
//	        "서울 강남구", "서울 성동구", "부산 해운대구", "대구 수성구", "광주 서구"
//	    };
//
//	    for (int i = 0; i < 5; i++) {
//	        // 1. 유저 저장 (컨설턴트 타입)
//	        User user = User
//	        		.builder()
//	                .email(emails[i])
//	                .password("pass1234")
//	                .name(names[i])
//	                .phone("0108888" + String.format("%04d", i))
//	                .address(addresses[i])
//	                .type(UserType.컨설턴트회원)
//	                .build();
//
//	        userRepository.save(user);
//
//	        // 2. 컨설턴트 저장 (User 기반)
//	        Consultant consultant = Consultant
//	        		.builder()
//	                .user(user)
//	                .build();
//
//	        consultantRepository.save(consultant);
//	    }
//
//	    em.flush();
//	    em.clear();
//	}
//
//	
//	
//	
//	@Test
//	@Rollback(false)
//	@DisplayName("Member에 Request + RequestDocument 정보 저장")
//	void saveRequestList() {
//	    List<Member> members = memberRepository.findAll();
//
//	    for (int i = 0; i < members.size(); i++) {
//	        Member member = members.get(i);
//
//	        // 요청 문서 생성
//	        RequestDocument requestDocument = RequestDocument
//	        		.builder()
//	                .resumeJson("{\"resume\": \"이력 내용 " + i + "\"}")
//	                .careerDescriptionJson("{\"career\": \"경력 내용 " + i + "\"}")
//	                .coverLetterJson("{\"coverLetter\": \"자소서 내용 " + i + "\"}")
//	                .build();
//
//	        requestDocumentRepository.save(requestDocument);
//
//	        // 요청 저장
//	        Request request = Request
//	        		.builder()
//	                .member(member)
//	                .requestDocument(requestDocument)
//	                .createdDate(LocalDate.now())
//	                .build();
//
//	        memberRequestRepository.save(request);
//	    }
//
//	    em.flush();
//	    em.clear();
//	}
//
//	@Test
//	@Rollback(false)
//	@DisplayName("AI Consulting 저장")
//	void saveAiConsulting() {
//	    List<Request> requestList = memberRequestRepository.findAll();
//
//	    for (int i = 0; i < requestList.size(); i++) {
//	        Request request = requestList.get(i);
//
//	        AiConsulting aiConsulting = AiConsulting
//	        		.builder()
//	                .request(request)
//	                .type(i % 2 == 0 ? CommonEnum.ConsultingType.피드백 : CommonEnum.ConsultingType.첨삭)
//	                .isRequested(true)
//	                .requestedDate(LocalDate.now())
//	                .build();
//
//	        aiConsultingRepository.save(aiConsulting);
//	    }
//
//	    em.flush();
//	    em.clear();
//	}
//
//	@Test
//	@Rollback(false)
//	@DisplayName("AiConsultingContent 저장")
//	void saveAiConsultingContents() {
//	    List<AiConsulting> aiConsultings = aiConsultingRepository.findAll();
//	    String[] items = { "경력사항", "자기소개", "기술스택", "목표", "의견" };
//	    String[] contents = {
//	        "10년간 백엔드 개발 경력",
//	        "열정과 책임감을 가진 개발자입니다.",
//	        "Spring, JPA, React",
//	        "AI 분석 기반 컨설팅 강화",
//	        "꼼꼼하고 분석력이 뛰어남"
//	    };
//
//	    for (int i = 0; i < aiConsultings.size(); i++) {
//	        AiConsulting aiconsulting = aiConsultings.get(i);
//
//	        AiConsultingContent aiConsultingContent = AiConsultingContent
//	        		.builder()
//	                .aiConsulting(aiconsulting)
//	                .documentType(i % 2 == 0 ? CommonEnum.DocumentType.경력기술서 : CommonEnum.DocumentType.자기소개서)
//	                .item(items[i])
//	                .content(contents[i])
//	                .build();
//
//	        aiConsultingContentRepository.save(aiConsultingContent);
//	    }
//
//	    em.flush();
//	    em.clear();
//	}
//	
//	@Test
//	@Rollback(false)
//	@DisplayName("ConsultantConsulting 저장")
//	void saveConsultantConsultings() {
//	    List<Consultant> consultants = consultantRepository.findAll();
//	    List<AiConsulting> aiConsultings = aiConsultingRepository.findAll();
//
//	    for (int i = 0; i < aiConsultings.size(); i++) {
//	        Consultant consultant = consultants.get(i);
//	        AiConsulting aiConsulting = aiConsultings.get(i);
//
//	        ConsultantConsulting consultantConsulting = ConsultantConsulting
//	        		.builder()
//	                .consultant(consultant)
//	                .aiConsulting(aiConsulting)
//	                .type(i % 2 == 0 ? CommonEnum.ConsultingType.피드백 : CommonEnum.ConsultingType.첨삭)
//	                .createdDate(LocalDate.now())
//	                .completedDate(LocalDate.now().plusDays(2))
//	                .build();
//
//	        consultantConsultingRepository.save(consultantConsulting);
//	    }
//
//	    em.flush();
//	    em.clear();
//	}
//	@Test
//	@Rollback(false)
//	@DisplayName("ConsultantConsultingContent 저장")
//	void saveConsultantConsultingContents() {
//	    List<ConsultantConsulting> consultantConsultings = consultantConsultingRepository.findAll();
//
//	    String[] items = { "경력사항", "자기소개", "기술스택", "목표", "의견" };
//	    String[] contents = {
//	        "10년간 백엔드 컨설팅 경험",
//	        "지원자와 적극 소통하는 스타일",
//	        "Spring, JPA, Vue 사용 경험",
//	        "커리어 성장 방향 제시",
//	        "피드백이 구체적이고 명확함"
//	    };
//
//	    for (int i = 0; i < consultantConsultings.size(); i++) {
//	        ConsultantConsulting consultantConsulting = consultantConsultings.get(i);
//
//	        ConsultantConsultingContent consultantConsultingContent = ConsultantConsultingContent
//	                .builder()
//	                .consultantConsulting(consultantConsulting)
//	                .documentType(i % 2 == 0 ? CommonEnum.DocumentType.경력기술서 : CommonEnum.DocumentType.자기소개서)
//	                .item(items[i])
//	                .content(contents[i])
//	                .build();
//
//	        consultantConsultingContentRepository.save(consultantConsultingContent);
//	    }
//
//	    em.flush();
//	    em.clear();
//	}
//
//	@Test
//	@DisplayName("AiConsulting ID 데이터 요청")
//	void findAiConsultingById() {
//	    // 조회할 AiConsulting ID
//	    Long aiConsultingId = 13L; 
//
//	    // 조회
//	    AiConsulting aiConsulting = aiConsultingRepository
//	    		.findWithAllRequestByAiConsultingId(aiConsultingId).
//	    		orElseThrow(() -> new IllegalArgumentException("해당 AiConsulting이 존재하지 않습니다."));
//
//	    // Member → User 정보 출력
//	    System.out.println("=== [Member → User 정보] ===");
//	    System.out.println("Member Nickname: " + aiConsulting.getRequest().getMember().getNickname());
//	    System.out.println("User Name: " + aiConsulting.getRequest().getMember().getUser().getName());
//	    System.out.println("User Email: " + aiConsulting.getRequest().getMember().getUser().getEmail());
//	    System.out.println("User Phone: " + aiConsulting.getRequest().getMember().getUser().getPhone());
//	    System.out.println("User Address: " + aiConsulting.getRequest().getMember().getUser().getAddress());
//
//	    // AiConsulting 기본 정보 출력
//	    System.out.println("\n=== [AiConsulting 정보] ===");
//	    System.out.println("AiConsulting ID: " + aiConsulting.getId());
//	    System.out.println("Requested Date: " + aiConsulting.getRequestedDate());
//	    System.out.println("Type: " + aiConsulting.getType());
//	    System.out.println("IsRequested: " + aiConsulting.isRequested());
//
//	    // Request 정보 출력
//	    System.out.println("\n=== [Request 정보] ===");
//	    System.out.println("Request ID: " + aiConsulting.getRequest().getId());
//	    System.out.println("Request Created Date: " + aiConsulting.getRequest().getCreatedDate());
//
//	    // RequestDocument 정보 출력
//	    RequestDocument requestDocument = aiConsulting.getRequest().getRequestDocument();
//	    System.out.println("\n=== [RequestDocument 정보] ===");
//	    System.out.println("Resume JSON: " + requestDocument.getResumeJson());
//	    System.out.println("Career Description JSON: " + requestDocument.getCareerDescriptionJson());
//	    System.out.println("Cover Letter JSON: " + requestDocument.getCoverLetterJson());
//
//	    // AiConsultingContent 리스트 출력
//	    List<AiConsultingContent> aiConsultingContents = aiConsulting.getAiConsultingContents();
//	    System.out.println("\n=== [AiConsultingContent 리스트] ===");
//	    System.out.println("AiConsultingContent 개수: " + aiConsultingContents.size());
//
//	    for (AiConsultingContent aiConsultingContent : aiConsultingContents) {
//	        System.out.println("\n-- AiConsultingContent --");
//	        System.out.println("ID: " + aiConsultingContent.getId());
//	        System.out.println("Item: " + aiConsultingContent.getItem());
//	        System.out.println("Content: " + aiConsultingContent.getContent());
//	        System.out.println("Document Type: " + aiConsultingContent.getDocumentType());
//	    }
//	    em.flush();
//	    em.clear();
//	}
//	
	
//	@Test
//	@Rollback(false)
//	@DisplayName("컨설팅 요청 대기중 목록 조회")
//	void findWaitingAiConsultings() {
//	    Pageable pageable = PageRequest.of(0, 10); // 첫 페이지, 10개씩
//
//	    Page<AiConsulting> page = aiConsultingRepository.findAllByCondition(pageable);
//
//	    System.out.println("총 건수: " + page.getTotalElements());
//	    List<String> list = new ArrayList<>();
//	    
//	    for (AiConsulting aiConsulting : page.getContent()) {
//	        System.out.println("AI ID: " + aiConsulting.getId() + ", 요청일: " + aiConsulting.getRequestedDate() +
//	                ", 요청자: " + aiConsulting.getRequest().getMember().getNickname());
//	        list.add(aiConsulting.getRequest().getMember().getNickname());
//	        
//	    }
//	    
//	    for(String s:list) {
//	    	System.out.println(s);
//	    }
//	    em.flush();
//	    em.clear();
//
//	}

//	@Test
//	@Rollback(false)
//	@DisplayName("컨설팅 요청 대기중 개수 조회")
//	void countWaitingAiConsultings() {
//	    long count = aiConsultingRepository.countAiConsultingByCondition();
//	    System.out.println("대기 중인 AiConsulting 개수: " + count);
//	}

	
	
	
	
	
//	@Test
//	@DisplayName("ConsultantConsulting ID 데이터 요청")
//	void findConsultantConsultingById() {
//	    // 조회할 ConsultantConsulting ID
//	    Long consultantConsultingId = 1L;
//
//	    // 조회
//	    ConsultantConsulting consultantConsulting = consultantConsultingRepository
//	            .findWithConsultantConsultingContentsByConsultantConsultingId(consultantConsultingId)
//	            .orElseThrow(() -> new IllegalArgumentException("해당 ConsultantConsulting이 존재하지 않습니다."));
//
//
//	    // ConsultantConsulting 기본 정보 출력
//	    System.out.println("\n=== [ConsultantConsulting 정보] ===");
//	    System.out.println("ID: " + consultantConsulting.getId());
//	    System.out.println("Created Date: " + consultantConsulting.getCreatedDate());
//	    System.out.println("Completed Date: " + consultantConsulting.getCompletedDate());
//	    System.out.println("Type: " + consultantConsulting.getType());
//
//	    // ConsultantConsultingContent 리스트 출력
//	    List<ConsultantConsultingContent> contents = consultantConsulting.getConsultantConsultingContents();
//	    System.out.println("\n=== [ConsultantConsultingContent 리스트] ===");
//	    System.out.println("ConsultantConsultingContent 개수: " + contents.size());
//
//	    for (ConsultantConsultingContent content : contents) {
//	        System.out.println("\n-- ConsultantConsultingContent --");
//	        System.out.println("ID: " + content.getId());
//	        System.out.println("Item: " + content.getItem());
//	        System.out.println("Content: " + content.getContent());
//	        System.out.println("Document Type: " + content.getDocumentType());
//	    }
//
//	    em.flush();
//	    em.clear();
//	}

	
//	@Test
//	@DisplayName("컨설턴트 ID로 ConsultantConsulting 페이징 조회")
//	void findConsultantConsultingsByConsultantId() {
//	    // given
//	    Long consultantId = 16L; // 컨설턴트 id
//	    Pageable pageable = PageRequest.of(0, 10); // 0페이지, 10개씩
//
//	   
//	    Page<ConsultantConsulting> page = consultantConsultingRepository
//	            .findWithAllConsultantConsultingByConsultantId(consultantId, pageable);
//
//	    
//	    System.out.println("총 건수: " + page.getTotalElements());
//
//	    for (ConsultantConsulting consulting : page.getContent()) {
//	        System.out.println("컨설팅 ID: " + consulting.getId());
//	        System.out.println("타입: " + consulting.getType());
//	        System.out.println("요청일: " + consulting.getCreatedDate());
//	        System.out.println("완료일: " + consulting.getCompletedDate());
//	    }
//
//	    em.flush();
//	    em.clear();
//	}

//	@Test
//	@DisplayName("컨설턴트가 작성한 컨설팅 개수 카운트")
//	void countConsultantConsultingsByConsultantId() {
//	   
//	    Long consultantId = 16L; // 컨설턴트 ID
//
//	    
//	    long count = consultantConsultingRepository.countConsultantConsultingByConsultantId(consultantId);
//
//	   
//	    System.out.println("컨설턴트 ID: " + consultantId);
//	    System.out.println("컨설팅 개수: " + count);
//
//	    assertTrue(count >= 0); 
//	}


	
	}

	    
	


	


	


//// Member → User 정보 출력 (Consultant → User)
//System.out.println("=== [Member → User 정보] ===");
//System.out.println("User Name: " + consultantConsulting.getConsultant().getUser().getName());
//System.out.println("User Email: " + consultantConsulting.getConsultant().getUser().getEmail());
//System.out.println("User Phone: " + consultantConsulting.getConsultant().getUser().getPhone());
//System.out.println("User Address: " + consultantConsulting.getConsultant().getUser().getAddress());
//
//
//// AiConsulting 정보 출력
//AiConsulting aiConsulting = consultantConsulting.getAiConsulting();
//System.out.println("\n=== [AiConsulting 정보] ===");
//System.out.println("ID: " + aiConsulting.getId());
//System.out.println("Requested Date: " + aiConsulting.getRequestedDate());
//System.out.println("Type: " + aiConsulting.getType());
//System.out.println("IsRequested: " + aiConsulting.isRequested());
//
//// Request 정보 출력
//Request request = aiConsulting.getRequest();
//System.out.println("\n=== [Request 정보] ===");
//System.out.println("Request ID: " + request.getId());
//System.out.println("Created Date: " + request.getCreatedDate());
//
//// Member → User 정보 출력 (요청자 기준)
//System.out.println("\n=== [요청자 Member → User 정보] ===");
//System.out.println("Member Nickname: " + request.getMember().getNickname());
//System.out.println("User Name: " + request.getMember().getUser().getName());
//System.out.println("User Email: " + request.getMember().getUser().getEmail());
//System.out.println("User Phone: " + request.getMember().getUser().getPhone());
//System.out.println("User Address: " + request.getMember().getUser().getAddress());
//
//// RequestDocument 정보 출력
//RequestDocument requestDocument = request.getRequestDocument();
//System.out.println("\n=== [RequestDocument 정보] ===");
//System.out.println("Resume JSON: " + requestDocument.getResumeJson());
//System.out.println("Career Description JSON: " + requestDocument.getCareerDescriptionJson());
//System.out.println("Cover Letter JSON: " + requestDocument.getCoverLetterJson());
//
//// AiConsultingContent 리스트 출력
//List<AiConsultingContent> aiContents = aiConsulting.getAiConsultingContents();
//System.out.println("\n=== [AiConsultingContent 리스트] ===");
//System.out.println("AiConsultingContent 개수: " + aiContents.size());
//
//for (AiConsultingContent aiContent : aiContents) {
//    System.out.println("\n-- AiConsultingContent --");
//    System.out.println("ID: " + aiContent.getId());
//    System.out.println("Item: " + aiContent.getItem());
//    System.out.println("Content: " + aiContent.getContent());
//    System.out.println("Document Type: " + aiContent.getDocumentType());
//}
//

