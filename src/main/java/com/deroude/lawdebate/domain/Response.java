/*
 * Copyright 2017  Valentin Raduti.
 * This source code file is the property of Valentin Raduti.
 * You are not allowed to view, edit, copy, re-use or re-engineer
 * this source code file unless specifically permissioned by Valentin Raduti.
 */
package com.deroude.lawdebate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author valentin.raduti
 */
@Entity
public class Response {

    public enum Position {
	YAY, NAY, ABSTAIN
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private Position position;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishDate;

    @Column
    @Basic(optional = false)
    private String text;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User author;

    @ManyToOne(optional = false)
    @JoinColumn(name = "article")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private LawArticle article;

    @OneToMany(mappedBy = "response")
    private List<Comment> comments;

    @OneToMany(mappedBy = "response")
    private List<ConfidenceVote> votes;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Position getPosition() {
	return position;
    }

    public void setPosition(Position position) {
	this.position = position;
    }

    public Date getPublishDate() {
	return publishDate;
    }

    public void setPublishDate(Date publishDate) {
	this.publishDate = publishDate;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public User getAuthor() {
	return author;
    }

    public void setAuthor(User author) {
	this.author = author;
    }

    @JsonIgnore
    public LawArticle getArticle() {
	return article;
    }

    public void setArticle(LawArticle article) {
	this.article = article;
    }

    public List<Comment> getComments() {
	return comments;
    }

    public void setComments(List<Comment> comments) {
	this.comments = comments;
    }

    public List<ConfidenceVote> getVotes() {
	return votes;
    }

    public void setVotes(List<ConfidenceVote> votes) {
	this.votes = votes;
    }

}
