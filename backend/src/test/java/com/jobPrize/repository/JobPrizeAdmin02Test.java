package com.jobPrize.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import com.jobPrize.config.QuerydslConfig;
import com.jobPrize.entity.common.Comment;
import com.jobPrize.entity.common.Post;
import com.jobPrize.repository.admin.admin.AdminRepository;
import com.jobPrize.repository.admin.editPrompt.EditPromptRepository;
import com.jobPrize.repository.admin.feedbackPrompt.FeedbackPromptRepository;
import com.jobPrize.repository.common.UserRepository;
import com.jobPrize.repository.common.comment.CommentRepository;
import com.jobPrize.repository.common.post.PostRepository;

import jakarta.persistence.EntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(QuerydslConfig.class)
public class JobPrizeAdmin02Test {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private EditPromptRepository editPromptRepository;

	@Autowired
	private FeedbackPromptRepository feedbackPromptRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private EntityManager em;

	private static final String[] 이름 = { "김철수", "이영희", "박지민", "최수연", "정우성", "강하늘", "조세호", "윤미래", "장동건", "임수정", "한가인",
			"서강준", "유재석", "신민아", "송중기", "배수지", "김연아", "이동욱", "이준기", "정해인" };

//	@Test
//	@Rollback(false)
//	@DisplayName("user 20명 저장 및 출력")
//	void createdUserInformation() {
//		for (int i = 0; i < 이름.length; i++) {
//			String name = 이름[i];
//			String email = "user" + (i + 1) + "@test.com";
//			String phone = "010-1234-" + String.format("%04d", i);
//			String address = "대전광역시 대덕구" + (i + 1) + "번지";
//			String password = "password12345";
//			UserType type = (i % 2 == 0) ? UserType.일반회원 : UserType.기업회원;
//			User user = User
//					.builder()
//					.name(name)
//					.email(email)
//					.phone(phone)
//					.address(address)
//					.password(password)
//					.type(type)
//					.build();
//
//			userRepository.save(user);
//
//			em.flush();
//			em.clear();
//		}
//	}

//	@Test
//	@Rollback(false)
//	@DisplayName("user 1명당 5개 Post 작성")
//	void postWriteByUser() {
//	    int postIndex = 1; // 전역 카운터
//
//	    for (int Id = 1; Id <= 20; Id++) {
//	        User user = userRepository.findById((long) Id)
//	                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 멤버가 없습니다."));
//
//	        for (int j = 1; j <= 5; j++) { 
//	            Post post = Post
//						.builder()
//	                    .user(user)
//	                    .title("제목 " + postIndex + "번째 제목")
//	                    .content("내용은 " + postIndex + "번째 내용입니다.")
//	                    .createdTime(LocalDateTime.now())
//	                    .build();
//
//	            postRepository.save(post);
//	            postIndex++; 
//	        }
//	    }
//
//	    em.flush();
//	    em.clear();
//	}

//	
//	@Test
//	@Rollback(false)
//	@DisplayName("post 갯수 세기")
//	void countPosts() {
//		long count = postRepository.count();
//		System.out.println("총 게시글 갯수 : " + count);
//		
//		em.flush();
//		em.clear();
//		
//	}

//	@Test
//	@Rollback(false)
//	@DisplayName("post 1개당 comment 5개 작성 (댓글 내용 순차 증가)")
//	void writeCommentsForEachPost() {
//	    List<Post> posts = postRepository.findAll();
//	    int commentIndex = 1;
//	    int userIndex = 1;
//
//	    for (int i = 0; i < posts.size(); i++) {
//	        Post post = posts.get(i);
//
//	        for (int j = 0; j < 5; j++) {
//	            if (userIndex > 20) userIndex = 1;
//
//	            User commenter = userRepository.findById((long) userIndex)
//	                    .orElseThrow(() -> new IllegalArgumentException("User ID 가 존재하지 않습니다."));
//
//	            Comment comment = Comment
//						.builder()
//	                    .post(post)
//	                    .user(commenter)
//	                    .content("댓글 내용 " + commentIndex + "번째 댓글입니다.")
//	                    .createdTime(LocalDateTime.now())
//	                    .build();
//
//	            commentRepository.save(comment);
//
//	            commentIndex++;
//	            userIndex++;
//	        }
//	    }
//
//	    em.flush();
//	    em.clear();
//	}
//}

//	@Test
//	@Rollback(false)
//	@DisplayName("post를 리스트로 출력")
//	void getPosts(){
//		Pageable pageable = PageRequest.of(0,20);
//		Map<String, Object> postMap=postRepository.findAllWithCommentsCount(pageable);
//
//		Page<Post> result = (Page<Post>)postMap.get("page");
//		List<Post> postList = result.getContent();
//		Map<Long, Long> commentCountMap = (Map<Long, Long>)postMap.get("count");
//		
//		for(Post post : postList) {
//			String title = post.getTitle();
//			String content = post.getContent();
//			String author = post.getUser().getName();
//			LocalDateTime writeDate = post.getCreatedTime();
//			int commentCount = commentCountMap.getOrDefault(post.getId(), 0L).intValue();
//			
//			
////			Long id = post.getId();
////			Post post1 = postRepository.findById(id)
////					.orElseThrow(() -> new IllegalArgumentException("게시글의 ID가 존재하지 않습니다" + id));
//			System.out.println("게시글 아이디: "+post.getId());
//			System.out.println("제목: "  + title);
//			System.out.println("내용: "  + content);
//			System.out.println("작성자 : " + author);
//			System.out.println("댓글 수: "  + commentCount);
//			System.out.println("작성 일자: " + writeDate);
////			List<Comment> comments = post.getComments();
////			for(Comment comment: comments){
////			System.out.println("댓글 내용: "  + comment.getContent());
//			}
//			
//		}
//
//	}

//	@Test
//	@Rollback(false)
//	@DisplayName("post 클릭시 작성자, 제목, 댓글 내용 출력")
//	void findWithCommentsByPostId() {
//		List<Post> posts = postRepository.findAll();
//
//		for (Post post : posts) {
//			String title = post.getTitle();
//			String content = post.getContent();
//			String author = post.getUser().getName();
//			LocalDateTime writeDate = post.getCreatedTime();
//			System.out.println("제목: " + title);
//			System.out.println("내용: " + content);
//			System.out.println("작성자: " + author);
//			System.out.println("작성 시간: " + writeDate);
//
//			List<Comment> comments = post.getComments();
//			for (Comment comment : comments) {
//
//				System.out.println("댓글 내용: " + comment.getContent());
//
//			}
//		}
//	}

//	@Test
//	@Rollback(false)
//	@DisplayName("feedbackPrompt 제목, 내용 작성 및 저장 1개만 적용됨" )
//	void writeFeedbackPromptAndSave() {
//		FeedbackPrompt resumeFeedbackPrompt1 = FeedbackPrompt
//				.builder()
//				.title("이력서 피드백 가이드1")
//				.content("너는 이력서 전문가1이야 회원의 이력서를 보고 말투 맞춤법 보안해야 할 부분 알려줘")
//				.isApplied(true)
//				.build();
//		
//		FeedbackPrompt resumeFeedbackPrompt2 = FeedbackPrompt
//				.builder()
//				.title("이력서 피드백 가이드2")
//				.content("너는 이력서 전문가2야 회원의 이력서를 보고 말투 맞춤법 보안해야 할 부분 알려줘")
//				.isApplied(false)
//				.build();
//		
//		FeedbackPrompt resumeFeedbackPrompt3 = FeedbackPrompt
//				.builder()
//				.title("이력서 피드백 가이드3")
//				.content("너는 이력서 전문가3이야 회원의 이력서를 보고 말투 맞춤법 보안해야 할 부분 알려줘")
//				.isApplied(false)
//				.build();
//		
//		FeedbackPrompt resumeFeedbackPrompt4 = FeedbackPrompt
//				.builder()
//				.title("이력서 피드백 가이드4")
//				.content("너는 이력서 전문가4야 회원의 이력서를 보고 말투 맞춤법 보안해야 할 부분 알려줘")
//				.isApplied(false)
//				.build();
//		
//		FeedbackPrompt resumeFeedbackPrompt5 = FeedbackPrompt
//				.builder()
//				.title("이력서 피드백 가이드5")
//				.content("너는 이력서 전문가5야 회원의 이력서를 보고 말투 맞춤법 보안해야 할 부분 알려줘")
//				.isApplied(false)
//				.build();
//		
//		FeedbackPrompt coverLetterFeedbackPrompt1 = FeedbackPrompt
//				.builder()
//				.title("자기소개서 피드백 가이드1")
//				.content("너는 자기소개서 전문가1이야 회원의 자기소개서를 말투 맞춤법 보안해야 할 부분 알려줘")
//				.isApplied(false)
//				.build();
//
//		FeedbackPrompt coverLetterFeedbackPrompt2 = FeedbackPrompt
//				.builder()
//				.title("자기소개서 피드백 가이드2")
//				.content("너는 자기소개서 전문가2야 회원의 자기소개서를 말투 맞춤법 보안해야 할 부분 알려줘")
//				.isApplied(false)
//				.build();
//		
//		FeedbackPrompt coverLetterFeedbackPrompt3 = FeedbackPrompt
//				.builder()
//				.title("자기소개서 피드백 가이드4")
//				.content("너는 자기소개서 전문가3이야 회원의 자기소개서를 말투 맞춤법 보안해야 할 부분 알려줘")
//				.isApplied(false)
//				.build();
//		
//		FeedbackPrompt coverLetterFeedbackPrompt4 = FeedbackPrompt
//				.builder()
//				.title("자기소개서 피드백 가이드4")
//				.content("너는 자기소개서 전문가4야 회원의 자기소개서를 말투 맞춤법 보안해야 할 부분 알려줘")
//				.isApplied(false)
//				.build();
//		
//		FeedbackPrompt coverLetterFeedbackPrompt5 = FeedbackPrompt
//				.builder()
//				.title("자기소개서 피드백 가이드5")
//				.content("너는 자기소개서 전문가5야 회원의 자기소개서를 말투 맞춤법 보안해야 할 부분 알려줘")
//				.isApplied(false)
//				.build();
//		
//		feedbackPromptRepository.save(resumeFeedbackPrompt1);
//		feedbackPromptRepository.save(resumeFeedbackPrompt2);
//		feedbackPromptRepository.save(resumeFeedbackPrompt3);
//		feedbackPromptRepository.save(resumeFeedbackPrompt4);
//		feedbackPromptRepository.save(resumeFeedbackPrompt5);
//		feedbackPromptRepository.save(coverLetterFeedbackPrompt1);
//		feedbackPromptRepository.save(coverLetterFeedbackPrompt2);
//		feedbackPromptRepository.save(coverLetterFeedbackPrompt3);
//		feedbackPromptRepository.save(coverLetterFeedbackPrompt4);
//		feedbackPromptRepository.save(coverLetterFeedbackPrompt5);
//	}
//}
//		

//		
//	}
//	}

//	@Test
//	@Rollback(false)
//	@DisplayName("editPrompt 제목, 내용 작성 및 저장 1개만 적용됨")
//	void writeEditPromptAndSave() {
//		EditPrompt resumeEditPrompt1 = EditPrompt
//				.builder()
//				.title("이력서 첨삭 가이드1")
//				.content("너는 이력서 전문가1이야 회원의 이력서를 보고 말투 맞춤법을 알맞게 고쳐줘")
//				.isApplied(false)
//				.build();
//		EditPrompt resumeEditPrompt2 = EditPrompt
//				.builder()
//				.title("이력서 첨삭 가이드2")
//				.content("너는 이력서 전문가2야 회원의 이력서를 보고 말투 맞춤법을 알맞게 고쳐줘")
//				.isApplied(false)
//				.build();
//		EditPrompt resumeEditPrompt3 = EditPrompt
//				.builder()
//				.title("이력서 첨삭 가이드3")
//				.content("너는 이력서 전문가3이야 회원의 이력서를 보고 말투 맞춤법을 알맞게 고쳐줘")
//				.isApplied(false)
//				.build();
//		EditPrompt resumeEditPrompt4 = EditPrompt
//				.builder()
//				.title("이력서 첨삭 가이드4")
//				.content("너는 이력서 전문가4야 회원의 이력서를 보고 말투 맞춤법을 알맞게 고쳐줘")
//				.isApplied(false)
//				.build();
//		EditPrompt resumeEditPrompt5 = EditPrompt
//				.builder()
//				.title("이력서 첨삭 가이드5")
//				.content("너는 이력서 전문가5야 회원의 이력서를 보고 말투 맞춤법을 알맞게 고쳐줘")
//				.isApplied(true)
//				.build();
//		
//		EditPrompt coverLetterEditPrompt1 = EditPrompt
//				.builder()
//				.title("자기소개서 점착 가이드1")
//				.content("너는 자기소개서 전문가1이야 회원의 자기소개서를 보고 말투 맞춤법을 알맞게 고쳐줘")
//				.isApplied(false)
//				.build();
//		EditPrompt coverLetterEditPrompt2 = EditPrompt
//				.builder()
//				.title("자기소개서 점착 가이드2")
//				.content("너는 자기소개서 전문가2야 회원의 자기소개서를 보고 말투 맞춤법을 알맞게 고쳐줘")
//				.isApplied(false)
//				.build();
//		
//		EditPrompt coverLetterEditPrompt3 = EditPrompt
//				.builder()
//				.title("자기소개서 점착 가이드3")
//				.content("너는 자기소개서 전문가3이야 회원의 자기소개서를 보고 말투 맞춤법을 알맞게 고쳐줘")
//				.isApplied(false)
//				.build();
//		
//		EditPrompt coverLetterEditPrompt4 = EditPrompt
//				.builder()
//				.title("자기소개서 점착 가이드4")
//				.content("너는 자기소개서 전문가4야 회원의 자기소개서를 보고 말투 맞춤법을 알맞게 고쳐줘")
//				.isApplied(false)
//				.build();
//		
//		EditPrompt coverLetterEditPrompt5 = EditPrompt
//				.builder()
//				.title("자기소개서 점착 가이드5")
//				.content("너는 자기소개서 전문가5야 회원의 자기소개서를 보고 말투 맞춤법을 알맞게 고쳐줘")
//				.isApplied(false)
//				.build();
//		
//		editPromptRepository.save(resumeEditPrompt1);
//		editPromptRepository.save(resumeEditPrompt2);
//		editPromptRepository.save(resumeEditPrompt3);
//		editPromptRepository.save(resumeEditPrompt4);
//		editPromptRepository.save(resumeEditPrompt5);
//		editPromptRepository.save(coverLetterEditPrompt1);
//		editPromptRepository.save(coverLetterEditPrompt2);
//		editPromptRepository.save(coverLetterEditPrompt3);
//		editPromptRepository.save(coverLetterEditPrompt4);
//		editPromptRepository.save(coverLetterEditPrompt5);
//		
//	}

//	@Test
//	@Rollback(false)
//	@DisplayName("editPrompt 전체 조회 및 적용 여부 표시")
//	void findAllAndAppliedPrompt() {
//	    List<EditPrompt> prompts = editPromptRepository.findAll();
//	    Optional<EditPrompt> appliedPrompt = editPromptRepository.findAppliedPrompt();
//
//	    for (EditPrompt prompt1 : prompts) {
//	        System.out.println("제목: " + prompt1.getTitle());
//
//	        if (appliedPrompt.isPresent() && prompt1.getId().equals(appliedPrompt.get().getId())) {
//	            System.out.println("=========='적용 중'========== ");
//	        }
//
//	        System.out.println("=========================================== ");
//	    }
//
//	}
//	}
//	
//	@Test
//	@Rollback(false)
//	@DisplayName("feedbackPrompt 전체 조회 및 적용 여부 표시")
//	void feedbackfindAllAppliedPrompt() {
//		List<FeedbackPrompt> prompts = feedbackPromptRepository.findAll();
//		Optional<FeedbackPrompt> appliedPrompt = feedbackPromptRepository.findAppliedPrompt();
//		
//		
//		for(FeedbackPrompt prompt1 : prompts) {
//			System.out.println("제목 : " + prompt1.getTitle());
//
//			if (appliedPrompt.isPresent() && prompt1.getId().equals(appliedPrompt.get().getId())) {
//				System.out.println("=========='적용 중'==========");
//		}
//		
//			System.out.println("=========================================== ");
//		}
//		
//		
//		}
//	}

	@Test
	@Rollback(false)
	@DisplayName("게시글 id를 통한 조회")
	void findByPostId() {
		Post post = postRepository.findWithCommentsByPostId(1L).orElseThrow();

		List<Comment> comments = post.getComments();

		System.out.println("제목 : " + post.getTitle());
		System.out.println("내용 : " + post.getContent());
		System.out.println("일시 : " + post.getCreatedTime());
		System.out.println("작성자 : " + post.getUser().getMember().getNickname());
		System.out.println("=========================================== ");

		for (Comment comment : comments) {
			System.out.println("댓글 내용 : " + comment.getContent());
			System.out.println("댓글 일시 : " + comment.getCreatedTime());
			System.out.println("=========================================== ");
		}
		

	}
	
//	@Test
//	@Rollback(false)
//	@DisplayName("게시글 id를 통한 조회")
//	void findByPostIdqwewq() {
//		Post post = postRepository.findById(1L).orElseThrow();
//
//		List<Comment> comments = post.getComments();
//
//		System.out.println("제목 : " + post.getTitle());
//		System.out.println("내용 : " + post.getContent());
//		System.out.println("일시 : " + post.getCreatedTime());
//		System.out.println("작성자 : " + post.getUser().getName());
//		System.out.println("=========================================== ");
//
//		for (Comment comment : comments) {
//			System.out.println("댓글 내용 : " + comment.getContent());
//			System.out.println("댓글 일시 : " + comment.getCreatedTime());
//			System.out.println("=========================================== ");
//		}
//
//	}

}