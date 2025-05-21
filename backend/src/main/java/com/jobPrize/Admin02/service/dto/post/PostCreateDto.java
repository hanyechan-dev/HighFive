package com.jobPrize.Admin02.service.dto.post;

import com.jobPrize.entity.common.Post;
import com.sun.istack.NotNull;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCreateDto {
		
	
	@NotBlank(message = "제목은 필수로 입력해야 합니다.")
	@Size(max = 50, message = "제목은 50자 이하로 입력해야 합니다.")
	private String title;
	
	@NotBlank(message = "내용은 필수로 입력해야 합니다.")
	@Size(max = 500, message = "내용은 500자 이하로 입력해야 합니다")
	private String content;
	
	
	public static PostCreateDto from(Post post) {
		return PostCreateDto 
				.builder()
				.title(post.getTitle())
				.content(post.getContent())
				.build();
	}

}
