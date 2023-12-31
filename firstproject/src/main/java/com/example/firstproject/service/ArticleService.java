package com.example.firstproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	
	public List<Article> index(){
		
		return articleRepository.findAll();
	}

	public Article show(Long id) {
		
		return articleRepository.findById(id).orElse(null);
	}
	
	public Article create(ArticleForm dto) {
		
		Article article = dto.toEntity();
		if(article.getId() != null) {
			return null;
		}
		
		return articleRepository.save(article);
	}
	
	public Article update(Long id, ArticleForm dto) {
		
		Article article = dto.toEntity();
		log.info("id: {}, article: {}", id, article.toString());
		
		Article target = articleRepository.findById(id).orElse(null);
		
		if(target==null || id!=article.getId()) {
			log.info("This is an invalid request. id: {}, article: {}", id, article.toString());
			return null;
		}
		
		target.patch(article);
		Article updated = articleRepository.save(target);
		
		return updated;
	}
	
	public Article delete(Long id) {
		Article target = articleRepository.findById(id).orElse(null);
		
		if(target == null) {
			return null;
		}
		
		articleRepository.delete(target);
		return target;
	}
	
	@Transactional
	public List<Article> createArticles(List<ArticleForm> dtos){
		// dto 묶음을 entity 묶음으로 변환
		List<Article> articleList = dtos.stream()
				.map(dto -> dto.toEntity())
				.collect(Collectors.toList());
		/*
		 * List<Article> articleList = new ArrayList<>();
		 * for(int i=0; i<dtso.size(); i++){
		 * 		ArticleForm dto = dtos.get(i);
		 * 		Article entity = dto.toEntity();
		 * 		articleList.add(entity);
		 * }
		 */
		
		// entity 묶음을 DB로 저장
		articleList.stream()
				.forEach(article -> articleRepository.save(article));
		/*
		 * for(int i=0; i<articleList.size(); i++){
		 * 		Article article = articleList.get(i);
		 * 		articleRepository.save(article);
		 */
		
		// 강제 예외 발생
		articleRepository.findById(-1L).orElseThrow(
				() -> new IllegalArgumentException("failure")
		);
		
		
		// 결과값 반환
		return articleList;
	}
}
