/*
 * Copyright 2017  Valentin Raduti.
 * This source code file is the property of Valentin Raduti.
 * You are not allowed to view, edit, copy, re-use or re-engineer
 * this source code file unless specifically permissioned by Valentin Raduti.
 */
package com.deroude.lawdebate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author valentin.raduti
 */
@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Basic(optional = false)
    private String text;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author")
    private User author;

    @ManyToOne(optional = false)
    @JoinColumn(name = "response")
    private Response response;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
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

    public User getAuthor() {
	return author;
    }

    public void setAuthor(User author) {
	this.author = author;
    }

    @JsonIgnore
    public Response getResponse() {
	return response;
    }

    public void setResponse(Response response) {
	this.response = response;
    }

}
