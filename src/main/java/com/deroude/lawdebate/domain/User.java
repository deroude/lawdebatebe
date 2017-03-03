/*
 * Copyright 2017  Valentin Raduti.
 * This source code file is the property of Valentin Raduti.
 * You are not allowed to view, edit, copy, re-use or re-engineer
 * this source code file unless specifically permissioned by Valentin Raduti.
 */
package com.deroude.lawdebate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author valentin.raduti
 */
@Entity
public class User {

    public enum Type {
	MP, LAW_OFFICER, REGULAR
    }

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    @Basic(optional = false)
    private String displayName;

    @Column
    @Enumerated(EnumType.STRING)
    private Type type;

    @OneToMany(mappedBy = "author")
    private List<LawArticle> articles;

    @OneToMany(mappedBy = "author")
    private List<Response> responses;

    @OneToMany(mappedBy = "author")
    private List<ConfidenceVote> votes;

    @OneToMany(mappedBy = "author")
    private List<Comment> comments;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getDisplayName() {
	return displayName;
    }

    public void setDisplayName(String displayName) {
	this.displayName = displayName;
    }

    public Type getType() {
	return type;
    }

    public void setType(Type type) {
	this.type = type;
    }

    @JsonIgnore
    public List<LawArticle> getArticles() {
	return articles;
    }

    public void setArticles(List<LawArticle> articles) {
	this.articles = articles;
    }

    @JsonIgnore
    public List<Response> getResponses() {
	return responses;
    }

    public void setResponses(List<Response> responses) {
	this.responses = responses;
    }

    @JsonIgnore
    public List<ConfidenceVote> getVotes() {
	return votes;
    }

    public void setVotes(List<ConfidenceVote> votes) {
	this.votes = votes;
    }

    @JsonIgnore
    public List<Comment> getComments() {
	return comments;
    }

    public void setComments(List<Comment> comments) {
	this.comments = comments;
    }

}
