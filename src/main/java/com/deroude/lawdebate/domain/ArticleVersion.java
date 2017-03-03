/*
 * Copyright 2017  Valentin Raduti.
 * This source code file is the property of Valentin Raduti.
 * You are not allowed to view, edit, copy, re-use or re-engineer
 * this source code file unless specifically permissioned by Valentin Raduti.
 */
package com.deroude.lawdebate.domain;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author valentin.raduti
 */
@Entity
public class ArticleVersion {

    public enum Status {
	PROPOSED, CANCELLED, IN_EFFECT
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    @Basic(optional = false)
    private String title;

    @Column
    @Basic(optional = false)
    private Boolean last = true;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishDate;

    @Column
    @Basic(optional = false)
    private String text;

    @ManyToOne(optional = false)
    @JoinColumn(name = "article")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private LawArticle article;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public Boolean getLast() {
	return last;
    }

    public void setLast(Boolean last) {
	this.last = last;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public Date getPublishDate() {
	return publishDate;
    }

    public void setPublishDate(Date publishDate) {
	this.publishDate = publishDate;
    }

    public LawArticle getArticle() {
	return article;
    }

    public void setArticle(LawArticle article) {
	this.article = article;
    }

    public Status getStatus() {
	return status;
    }

    public void setStatus(Status status) {
	this.status = status;
    }

}
