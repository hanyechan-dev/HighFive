package com.jobPrize;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import com.jobPrize.config.QuerydslConfig;
import com.jobPrize.entity.common.Post;
import com.jobPrize.repository.admin.admin.AdminRepository;
import com.jobPrize.repository.admin.editPrompt.EditPromptRepository;
import com.jobPrize.repository.admin.feedbackPrompt.FeedbackPromptRepository;
import com.jobPrize.repository.common.UserRepository;
import com.jobPrize.repository.common.comment.CommentRepository;
import com.jobPrize.repository.common.post.PostRepository;

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

//	 private static final String[] 이름 = 
//					{ "김철수", "이영희", "박지민", "최수연", "정우성", 
//					  "강하늘", "조세호", "윤미래", "장동건", "임수정", 
//					  "한가인", "서강준", "유재석", "신민아", "송중기", 
//					  "배수지", "김연아", "이동욱", "이준기", "정해인" };

//	@Test
//	@Rollback(false)
//	@DisplayName("user 20명 저장 및 출력")
//	void createdUserInformation() {
//		for (int i = 0; i < 이름.length; i++) {
//			String name = 이름[i];
//			String email = "user" + (i + 1) + "@test.com";
//			String phone = "010-1234-5678" + String.format("%04d",i);
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
//	}
//}

//	@Test
//	@Rollback(false)
//	@DisplayName("user 1명당 5개 Post 작성")
//	void postWriteByUser() {
//		for (int Id = 1; Id <= 20; Id++) {
//			User user = userRepository.findById((long) Id)
//					.orElseThrow(() -> new IllegalArgumentException("해당 ID의 멤버가 없습니다."));
//
//			for (int j = 1; j <= 5; j++) {
//				Post post = Post
//						.builder()
//						.user(user)
//						.title("제목 user" + j + "번째 제목")
//						.content("내용은 " + j + "번째 내용입니다.")
//						.createdTime(LocalDateTime.now())
//						.build();
//
//				postRepository.save(post);
//			}
//		}
//
//	}

//	@Test
//	@Rollback(false)
//	@DisplayName("Post 1개당 comment 5개 작성")
//	void commentWirteAndSave() {
//		List<Post> posts = postRepository.findAll();
//
//		for (int i = 0; i < 20; i++) {
//			Post post = posts.get(i);
//
//			for (int j = 1; j <= 5; j++) {
//				Long id = (long) ((i * 5 + j - 1) % 5 + 1);
//				
//				User commenter = userRepository.findById(id)
//						.orElseThrow(() -> new IllegalArgumentException("User ID " + id + "가 존재하지 않습니다."));
//				Comment comment = Comment
//						.builder()
//						.post(post)
//						.user(commenter)
//						.content("댓글 내용" + j + "번째 댓글입니다")
//						.createdTime(LocalDateTime.now())
//						.build();
//
//				commentRepository.save(comment);
//			}
//		}
//
//	}

	

	@Test
	@Rollback(false)
	@DisplayName("게시글 리스트로 출력")
	void getPosts(){
		Pageable pageable = PageRequest.of(0,20);
		Map<String, Object> postMap=postRepository.findAllWithCommentsCount(pageable);

		Page<Post> result = (Page<Post>)postMap.get("page");
		List<Post> postList = result.getContent();
		System.out.println("조회된 게시글 수: " + postList.size());
//		Map<Long, Long> commentCountMap = (Map<Long, Long>)postMap.get("count");
//		commentCountMap.get()
		for(Post post : postList) {
			String title = post.getTitle();
			String content = post.getContent();
			String author = post.getUser().getName();
//			int commentCount = post.getComments().size();
			LocalDateTime writeDate = post.getCreatedTime();
			
			
//			Long id = post.getId();
//			Post post1 = postRepository.findById(id)
//					.orElseThrow(() -> new IllegalArgumentException("게시글의 ID가 존재하지 않습니다" + id));
			
			System.out.println("제목: "  + title);
			System.out.println("내용: "  + content);
			System.out.println("작성자 : " + author);
//			System.out.println("댓글 수: "  + commentCount);
//			List<Comment> comments = post.getComments();
//			for(Comment comment: comments){
//			System.out.println("댓글 내용: "  + comment.getContent());
			}
			
		}
	}
		
//	}
	
//	@Test
//	@Rollback(false)
//	@DisplayName("feedbackPrompt 제목, 내용 작성 및 저장 1개만 적용됨" )
//	void writeFeedbackPromptAndSave() {
//		FeedbackPrompt resumeFeedbackPrompt = FeedbackPrompt
//				.builder()
//				.title("이력서 피드백 가이드")
//				.content("너는 이력서 전문가야 회원의 이력서를 보고 말투 맞춤법 보안해야 할 부분 알려줘")
//				.isApplied(true)
//				.build();
//		
//		FeedbackPrompt coverLetterFeedbackPrompt = FeedbackPrompt
//				.builder()
//				.title("자기소개서 피드백 가이드")
//				.content("너는 자기소개서 전문가야 회원의 자기소개서를 말투 맞춤법 보안해야 할 부분 알려줘")
//				.isApplied(false)
//				.build();
//		
//		feedbackPromptRepository.save(resumeFeedbackPrompt);
//		feedbackPromptRepository.save(coverLetterFeedbackPrompt);
//		
//		
//	}
	
	
	
//	@Test
//	@Rollback(false)
//	@DisplayName("editPrompt 제목, 내용 작성 및 저장 1개만 적용됨")
//	void writeEditPromptAndSave() {
//		EditPrompt resumeEditPrompt = EditPrompt
//				.builder()
//				.title("이력서 첨삭 가이드")
//				.content("너는 이력서 전문가야 회원의 이력서를 보고 말투 맞춤법을 알맞게 고쳐줘")
//				.isApplied(false)
//				.build();
//		
//		EditPrompt coverLetterEditPrompt = EditPrompt
//				.builder()
//				.title("자기소개서 점착 가이드")
//				.content("너는 자기소개서 전문가야 회원의 자기소개서를 보고 말투 맞춤법을 알맞게 고쳐줘")
//				.isApplied(true)
//				.build();
//		
//		editPromptRepository.save(resumeEditPrompt);
//		editPromptRepository.save(coverLetterEditPrompt);
//	}
//	
	


