package com.example.firstproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ArticleController {
	
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/articles/new")
	public String newArticleForm() {
		return "articles/new";
	}
	
	@PostMapping("/articles/create")
	public String createAricle(ArticleForm form) {
		log.info(form.toString());
		
		Article article = form.toEntity();
		log.info(article.toString());
		
		Article saved = articleRepository.save(article);
		log.info(saved.toString());
		
		return "redirect:/articles/" + saved.getId();
	}
	
	@GetMapping("/articles/{id}")
	public String show(@PathVariable Long id, Model model) {
		log.info("id = " + id);
		
		Article articeEntity = articleRepository.findById(id).orElse(null);
		
		List<CommentDto> commentsDtos = commentService.comments(id);
		
		model.addAttribute("article", articeEntity);
		model.addAttribute("commentsDtos", commentsDtos);
		
		return "articles/show";
	}
	
	@GetMapping("/articles")
	public String index(Model model) {
		
		List<Article> articeEntityList = articleRepository.findAll();
		
		model.addAttribute("articleList", articeEntityList);
		
		return "articles/index";
	}
	
	@GetMapping("/articles/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {
		
		Article articleEntity = articleRepository.findById(id).orElse(null);
		
		model.addAttribute("article", articleEntity);
		
		return "articles/edit";
	}
	
	@PostMapping("/articles/update")
	public String update(ArticleForm form) {
		log.info(form.toString());
		
		Article articleEntity = form.toEntity();
		log.info(articleEntity.toString());
		
		Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
		if(target != null) {
			articleRepository.save(articleEntity);
		}
		
		return "redirect:/articles/" + articleEntity.getId();
	}
	
	@GetMapping("/articles/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes rttr) {
		log.info("A deletion request has been received.");
		
		Article target = articleRepository.findById(id).orElse(null);
		log.info(target.toString());
		
		if(target != null) {
			articleRepository.delete(target);
			rttr.addFlashAttribute("msg", "Deletion is complete");
		}
		
		return "redirect:/articles";
	}
}