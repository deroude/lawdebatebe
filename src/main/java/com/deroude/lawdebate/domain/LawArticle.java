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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author valentin.raduti
 */
@Entity
public class LawArticle {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Basic(optional = false)
    private String proposedBy;

    @Column
    @Basic(optional = false)
    private String agency;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User author;

    @OneToMany(mappedBy = "article")
    private List<ArticleVersion> versions;

    @OneToMany(mappedBy = "article")
    private List<Response> responses;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getProposedBy() {
	return proposedBy;
    }

    public void setProposedBy(String proposedBy) {
	this.proposedBy = proposedBy;
    }

    public String getAgency() {
	return agency;
    }

    public void setAgency(String agency) {
	this.agency = agency;
    }

    public User getAuthor() {
	return author;
    }

    public void setAuthor(User author) {
	this.author = author;
    }

    @JsonIgnore
    public List<ArticleVersion> getVersions() {
	return versions;
    }

    public void setVersions(List<ArticleVersion> versions) {
	this.versions = versions;
    }

    @JsonIgnore
    public List<Response> getResponses() {
	return responses;
    }

    public void setResponses(List<Response> responses) {
	this.responses = responses;
    }

}
