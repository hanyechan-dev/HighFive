package com.jobPrize.Admin02.service.dto.post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.jobPrize.entity.common.Comment;
import com.jobPrize.entity.common.Post;
import com.jobPrize.entity.common.User;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostContentResponseDto {
	
	private Long id;
	private String title;
	private String content;
	private String authorId;
	private List<String> commentsContent;
	private LocalDateTime createdDate;
	
	
	public static PostContentResponseDto from(Post post) {
		User user = post.getUser();
		List<Comment> comments = post.getComments();
		
		List<String> commentContents=new ArrayList<>();
		for(Comment comment:comments) {
			commentContents.add(comment.getContent());
		}
		return PostContentResponseDto
				.builder()
				.id(post.getId())
				.title(post.getTitle())
				.content(post.getContent())
				.authorId(user.getName())
				.commentsContent(commentContents)
				.createdDate(post.getCreatedTime())
				.build();
	}
	

}
