package com.jobPrize.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post")		//게시글 테이블
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Long postId;		// 게시글 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)		//작성자
    private User author;

    @Column(nullable = false)
    private String title;		//게시글 제목

    @Column(nullable = false)
    private String content;	//게시글 내용
    
    @CreatedDate
    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdTime;		//작성 시간
    
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

	public void updatePost(String title, String content) {
		this.title = title;
		this.content = content;				//게시글 제목, 게시글 내용을 업데이트 처리하는 메서드
	}
    

    
    
    
    
    
    
    
}
