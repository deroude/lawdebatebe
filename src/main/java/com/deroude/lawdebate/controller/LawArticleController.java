/*
 * Copyright 2017  Valentin Raduti.
 * This source code file is the property of Valentin Raduti.
 * You are not allowed to view, edit, copy, re-use or re-engineer
 * this source code file unless specifically permissioned by Valentin Raduti.
 */
package com.deroude.lawdebate.controller;

import com.deroude.lawdebate.domain.ArticleVersion;
import com.deroude.lawdebate.domain.Comment;
import com.deroude.lawdebate.domain.LawArticle;
import com.deroude.lawdebate.domain.Response;
import com.deroude.lawdebate.dto.LawArticleSummary;
import com.deroude.lawdebate.repository.ArticleVersionRepository;
import com.deroude.lawdebate.repository.CommentRepository;
import com.deroude.lawdebate.repository.LawArticleRepository;
import com.deroude.lawdebate.repository.ResponseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author valentin.raduti
 */
@RestController
@RequestMapping("/article")
public class LawArticleController {

    private final LawArticleRepository lawArticleRepository;
    private final ResponseRepository responseRepository;
    private final CommentRepository commentRepository;
    private final ArticleVersionRepository articleVersionRepository;

    public LawArticleController(LawArticleRepository lawArticleRepository,
	    ResponseRepository responseRepository,
	    CommentRepository commentRepository,
	    ArticleVersionRepository articleVersionRepository) {
	this.lawArticleRepository = lawArticleRepository;
	this.responseRepository = responseRepository;
	this.commentRepository = commentRepository;
	this.articleVersionRepository = articleVersionRepository;
    }

    @GetMapping(produces = "application/json")
    public Page<LawArticleSummary> getArticles(@RequestParam(value = "search", defaultValue = "", required = false) String search,
	    @PageableDefault(page = 0, size = 20) Pageable pgreq) {
	return lawArticleRepository.getArticles(search, pgreq);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<LawArticleSummary> getArticle(@PathVariable("id") Long id) {
	LawArticleSummary re = lawArticleRepository.getArticle(id);
	if (re == null) {
	    return ResponseEntity.notFound().build();
	}
	return ResponseEntity.ok(re);
    }

    @GetMapping(value = "/{id}/versions", produces = "application/json")
    public Page<ArticleVersion> getVersions(@PathVariable("id") Long id, @PageableDefault(page = 0, size = 20) Pageable pgreq) {
	return articleVersionRepository.findByArticleId(id, pgreq);
    }

    @GetMapping(value = "/{id}/responses", produces = "application/json")
    public Page<Response> getResponses(@PathVariable("id") Long id, @PageableDefault(page = 0, size = 20) Pageable pgreq) {
	return responseRepository.findByArticleId(id, pgreq);
    }

    @GetMapping(value = "/{articleId}/{responseId}/comments", produces = "application/json")
    public Page<Comment> getComments(@PathVariable("articleId") Long articleId, @PathVariable("responseId") Long responseId, @PageableDefault(page = 0, size = 20) Pageable pgreq) {
	return commentRepository.findByResponseId(responseId, pgreq);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity addArticle(@RequestBody LawArticle article) {
	lawArticleRepository.save(article);
	return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{articleId}", consumes = "application/json")
    public ResponseEntity addResponse(@PathVariable("articleId") Long articleId, @RequestBody Response response) {
	LawArticle re = lawArticleRepository.findOne(articleId);
	if (re == null) {
	    return ResponseEntity.notFound().build();
	} else {
	    response.setArticle(re);
	    responseRepository.save(response);
	    return ResponseEntity.ok().build();
	}
    }

    @PostMapping(value = "/{articleId}/{responseId}", consumes = "application/json")
    public ResponseEntity addComment(@PathVariable("articleId") Long articleId, @PathVariable("responseId") Long responseId, @RequestBody Comment comment) {
	Response re = responseRepository.findOne(responseId);
	if (re == null) {
	    return ResponseEntity.notFound().build();
	} else {
	    comment.setResponse(re);
	    commentRepository.save(comment);
	    return ResponseEntity.ok().build();
	}
    }
}
