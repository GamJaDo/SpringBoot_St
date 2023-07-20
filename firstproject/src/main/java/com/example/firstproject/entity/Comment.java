package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne // 해당 댓글 엔티티 여러개가, 하나의 Article에 연관된다.
	@JoinColumn(name = "article_id")
	private Article article;
	
	@Column
	private String nickname;
	
	@Column
	private String body;
	
	public static Comment createComent(CommentDto dto, Article article) {
		// 예외 처리
		if(dto.getId() != null) {
			throw new IllegalArgumentException("Failed to write comment.");
		}
		if(!(dto.getArticleId()).equals(article.getId())) {
			throw new IllegalArgumentException("Failed to write comment.");
		}
		
		// 엔티티 생성 및 반환
		return new Comment(
				dto.getId(),
				article,
				dto.getNickname(),
				dto.getBody()
		);
	}

	public void patch(CommentDto dto) {
		// 예외 발생
		if(this.id != dto.getId()) {
			throw new IllegalArgumentException("Failed to write comment.");
		}
		
		// 객체를 갱신
		if(dto.getNickname() != null) {
			this.nickname = dto.getNickname();
		}
		if(dto.getBody() != null) {
			this.body = dto.getBody();
		}
	}
}
